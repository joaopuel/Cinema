import { Time } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { CinemaInfo, Filme, SessaoPayload } from 'src/app/types/types';

@Component({
  selector: 'app-sessoes-adm-screen',
  templateUrl: './sessoes-adm-screen.component.html',
  styleUrls: ['./sessoes-adm-screen.component.css']
})
export class SessoesAdmScreenComponent implements OnInit {

  diaSemana: string[] = ["Dom","Seg","Ter","Qua","Qui","Sex","Sáb"];

  filme!: Filme | null;

  nomeFilme!: string | null;

  cinemas: CinemaInfo[] = [];

  salasSelecionadas: number[] = [];

  sessaoForm: FormGroup = this.formBuilder.group({
    dataInicio: ['', Validators.required],
    dataFim: ['', Validators.required],
    horario: ['', Validators.required],
    valorInteira: ['', Validators.required],
    taxaVip: ['', Validators.required],
    tipoSessao: ['', Validators.required]
  });

  constructor(
    private formBuilder: FormBuilder,
    private acttivateRoute: ActivatedRoute, 
    private http: HttpClient) {
  }

  ngOnInit(): void {
    this.nomeFilme = this.acttivateRoute.snapshot.paramMap.get('nome');
    this.http.get<Filme>(`/filmes/${this.nomeFilme}`).subscribe((filme) => this.filme = filme);
    this.http.get<CinemaInfo[]>("/usuario/meuscinemas").subscribe((cinemas) => this.cinemas = cinemas);
  }

  sessoesPorCinemaPorDia(nomeCinema: String, d: string) {
    let dia: Date = new Date(d);
    return this.filme?.sessoes.filter((s) => s.nomeCinema === nomeCinema).filter((s) => s.dataSessao.toString().split('T')[0] === (dia.getFullYear() + "-" + (dia.getMonth()+1) + "-" + dia.getDate()));
  }

  listaCinemas = () => {
    let listaCinemas: Set<string> = new Set();
    this.filme?.sessoes.forEach((s) => {
        listaCinemas.add(s.nomeCinema);
    });
    let cinemas: string[] = [];
    listaCinemas.forEach((c) => cinemas.push(c));
    return cinemas.sort();
  }

  today = () => {
    return new Date();
  }

  getDias(cinema: string){
    let dias: Set<string> = new Set();
    this.filme?.sessoes.filter((s) => s.nomeCinema == cinema).forEach((ss) => {
      let dia: string = new Date(ss.dataSessao).toLocaleDateString('en-us', { year: 'numeric', month: 'numeric', day: 'numeric'});
      dias.add(dia);
    });
    let listDias: string[] = [];
    dias.forEach((d) => listDias.push(d));
    return listDias.sort();
  }

  getMyDate(d: string){
    return new Date(d);
  }

  getSalas(c: CinemaInfo){
    return this.cinemas.find((cn) => cn.id == c.id)?.salas;
  }

  addSala(id: number){
    if(this.salasSelecionadas.includes(id)){
      this.salasSelecionadas.splice(this.salasSelecionadas.indexOf(id), 1);
    }else{
      this.salasSelecionadas.push(id);
    }
  }

  getDiasEntreDatas(){
    let dataFim: Date = new Date(this.sessaoForm.value.dataFim);
    let dataInicio: Date = new Date(this.sessaoForm.value.dataInicio);
    let oneDay = 24 * 60 * 60 * 1000;
    return Math.round(Math.abs((dataFim.getTime() - dataInicio.getTime()) / oneDay)) + 1;
  }

  addSessoes(){
    let sessoes: SessaoPayload[] = [];
    this.salasSelecionadas.forEach(
      (s) => {
        for(let i=1; i<=this.getDiasEntreDatas(); i++){
          let d: Date = new Date(this.sessaoForm.value.dataInicio);
          let time: string = this.sessaoForm.value.horario;
          let nextDay = new Date("" + d.getFullYear() + "-" + (d.getMonth()+1) + "-" + d.getDate() + "T" + time + ":00");
          nextDay.setDate(nextDay.getDate()+i);
          nextDay.setHours(nextDay.getHours()-3);
          let sessao: SessaoPayload = {
            dataSessao: nextDay,
            idFilme: this.filme!.id,
            idSala: s,
            valorInteira: this.sessaoForm.value.valorInteira,
            taxaVip: this.sessaoForm.value.taxaVip,
            tipoSessao: this.sessaoForm.value.tipoSessao
          }
          sessoes.push(sessao);
        }
      });
      console.log(sessoes);
      this.http.post<SessaoPayload[]>("/sessoes/listasessoes", sessoes).subscribe(
        (sucess) => {
          window.location.reload();
        },
        (error) => {
          console.log(error);
        }
      );
  }
}
