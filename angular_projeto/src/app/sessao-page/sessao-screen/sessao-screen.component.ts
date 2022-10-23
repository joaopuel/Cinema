import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CadeiraInfo, SessaoInfo } from 'src/app/types/types';

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

  constructor(private acttivateRoute: ActivatedRoute, private http: HttpClient) {
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
    return array;
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
}
