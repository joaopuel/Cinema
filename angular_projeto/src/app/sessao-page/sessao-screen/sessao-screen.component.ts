import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CadeiraInfo, IngressoPayload, SessaoInfo } from 'src/app/types/types';

@Component({
  selector: 'app-sessao-screen',
  templateUrl: './sessao-screen.component.html',
  styleUrls: ['./sessao-screen.component.css']
})
export class SessaoScreenComponent implements OnInit {

  diaSemana: string[] = ["Dom","Seg","Ter","Qua","Qui","Sex","Sáb"];

  idSessao!: string | null;

  sessao!: SessaoInfo;

  cadeirasSelecionadas: CadeiraInfo[] = [];
  
  cardSelecionado: string = 'cadeiras';

  listaIngressos: IngressoPayload[] = [];

  numInteiraPadrao: number = 0;

  numMeiaPadrao: number = 0;

  numInteiraVIP: number = 0;

  numMeiaVIP: number = 0;

  total: number = 0;

  formaPagamento: string = '';

  constructor(
    private acttivateRoute: ActivatedRoute, 
    private http: HttpClient,
    private router: Router) {
  }

  ngOnInit(): void {
    this.idSessao = this.acttivateRoute.snapshot.paramMap.get('id');
    this.http.get<SessaoInfo>(`/sessoes/${this.idSessao}`).subscribe((sessao) => this.sessao = sessao);
  }

  getCadeirasByFileira = (fileira: number) => {
    let listaCadeiras: (CadeiraInfo | undefined)[] = [];
    for(let i=1; i <= this.sessao.sala.tamFileiras; i++){
      listaCadeiras.push(this.sessao.sala.cadeiras.filter((c) => c.fileira === fileira).find((c) => c.ordemFileira === i));
    }
    return listaCadeiras;
  }

  getFileiras = () => {
    return this.range(this.sessao.sala.numFileiras, 1);
  }

  range(size: number, startAt: number) {
    let array: number[] = [];
    for(let i=startAt; i<= size; i++){
      array.push(i);
    }
    return array.reverse();
  }

  getMyDate() {
    return new Date(this.sessao.dataSessao);
  }

  onClick(cadeira: CadeiraInfo) {
    if (this.cadeirasSelecionadas.includes(cadeira)) {
      this.cadeirasSelecionadas.splice(this.cadeirasSelecionadas.indexOf(cadeira), 1);
    } else {
      this.cadeirasSelecionadas.push(cadeira);
    }
  }

  getCadeirasSelecionadas = () => {
    return this.cadeirasSelecionadas;
  }

  setCardSelecionado(tipo: string){
    this.cardSelecionado = tipo;
  }

  getCadeirasSelecionadasPeloTipo = (tipo: string) => {
    this.cadeirasSelecionadas.filter((c) => c.tipoCadeira === tipo).forEach((c) => c.ocupado = false);
    for(let i=0; i < (this.numInteiraPadrao + this.numMeiaPadrao); i++){
      this.cadeirasSelecionadas.filter((c) => c.tipoCadeira === 'Padrão')[i].ocupado = true;
    }
    for(let i=0; i < (this.numInteiraVIP + this.numMeiaVIP); i++){
      this.cadeirasSelecionadas.filter((c) => c.tipoCadeira === 'VIP')[i].ocupado = true;
    }
    return this.cadeirasSelecionadas.filter((c) => c.tipoCadeira === tipo);
  }

  setQtdTipoEntrada(tipo: string, meia: boolean, numero: number){
    this.total = 0;
    if(tipo === 'Padrão'){
      if(meia){
        this.numMeiaPadrao = numero;
      } else{
        this.numInteiraPadrao = numero;
      }
    } else{
      if(meia){
        this.numMeiaVIP = numero;
      } else {
        this.numInteiraVIP = numero;
      }
    }
    this.total = (this.numInteiraPadrao * this.sessao.valorInteira) + ((this.numMeiaPadrao * this.sessao.valorInteira)/2) + (this.numInteiraVIP * (this.sessao.valorInteira+this.sessao.taxaVip)) + ((this.numMeiaVIP * (this.sessao.valorInteira+this.sessao.taxaVip))/2);
  }

  getIngressos(){
    this.listaIngressos = [];
    let cadeirasPadrao: CadeiraInfo [] = this.cadeirasSelecionadas.filter((c) => c.tipoCadeira === 'Padrão');
    let cadeirasVIP: CadeiraInfo [] = this.cadeirasSelecionadas.filter((c) => c.tipoCadeira === 'VIP');

    for(let i = 0; i<this.numInteiraPadrao; i++){
      let cadeira: (CadeiraInfo | undefined) = cadeirasPadrao.pop();
      if(cadeira != undefined && cadeira != null){
        let ingresso: IngressoPayload = {
          idSessao: this.sessao.id,
          idCadeira: cadeira.id,
          meiaEntrada: false
        }
        this.listaIngressos.push(ingresso);
      }
    }
    for(let i = 0; i<this.numMeiaPadrao; i++){
      let cadeira: (CadeiraInfo | undefined) = cadeirasPadrao.pop();
      if(cadeira != undefined && cadeira != null){
        let ingresso: IngressoPayload = {
          idSessao: this.sessao.id,
          idCadeira: cadeira.id,
          meiaEntrada: true
        }
        this.listaIngressos.push(ingresso);
      }
    }
    for(let i = 0; i<this.numInteiraVIP; i++){
      let cadeira: (CadeiraInfo | undefined) = cadeirasVIP.pop();
      if(cadeira != undefined && cadeira != null){
        let ingresso: IngressoPayload = {
          idSessao: this.sessao.id,
          idCadeira: cadeira.id,
          meiaEntrada: false
        }
        this.listaIngressos.push(ingresso);
      }
    }
    for(let i = 0; i<this.numMeiaVIP; i++){
      let cadeira: (CadeiraInfo | undefined) = cadeirasVIP.pop();
      if(cadeira != undefined && cadeira != null){
        let ingresso: IngressoPayload = {
          idSessao: this.sessao.id,
          idCadeira: cadeira.id,
          meiaEntrada: true
        }
        this.listaIngressos.push(ingresso);
      }
    }
    return this.listaIngressos;
  }

  setPagamento(tipo: string){
    this.formaPagamento = tipo;
  }

  comprarIngressos(){
    this.http.put<number>("/usuario/movimentacao", this.total).subscribe((sucess) => {
      this.http.post<IngressoPayload[]>("/ingressos/listaingressos", this.listaIngressos).subscribe(
        (sucess) => {
          this.router.navigate(["/meusingressos"]);
        },
        (error) => {
          console.log(error);
        }
      )
    },
    (error) => {console.log(error)});
  }
}
