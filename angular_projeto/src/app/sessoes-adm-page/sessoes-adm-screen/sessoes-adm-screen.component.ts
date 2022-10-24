import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Filme } from 'src/app/types/types';

@Component({
  selector: 'app-sessoes-adm-screen',
  templateUrl: './sessoes-adm-screen.component.html',
  styleUrls: ['./sessoes-adm-screen.component.css']
})
export class SessoesAdmScreenComponent implements OnInit {

  filme!: Filme | null;

  nomeFilme!: string | null;

  dia: Date = new Date();

  sessaoForm: FormGroup = this.formBuilder.group({
    id: [null],
    codigo: ['', Validators.required],
    tipoCadeira: ['', Validators.required],
    fileira: ['', Validators.required],
    ordemFileira: ['', Validators.required]
  });

  constructor(
    private formBuilder: FormBuilder,
    private acttivateRoute: ActivatedRoute, 
    private http: HttpClient) {
  }

  ngOnInit(): void {
    this.nomeFilme = this.acttivateRoute.snapshot.paramMap.get('nome');
    this.http.get<Filme>(`/filmes/${this.nomeFilme}`).subscribe((filme) => this.filme = filme);
  }

  sessoesPorCinema(nomeCinema: String) {
    return this.filme?.sessoes.filter((ss) => (ss.nomeCinema === nomeCinema) && (ss.dataSessao.toString().split('T')[0] === (this.dia.getFullYear() + '-' + (this.dia.getMonth()+1) + '-' + this.dia.getDate())));
  }

  listaCinemas = () => {
    let listaCinemas: Set<string> = new Set();
    this.filme?.sessoes.forEach((s) => {
      if(s.dataSessao.toString().split('T')[0] === (this.dia.getFullYear() + '-' + (this.dia.getMonth()+1) + '-' + this.dia.getDate())){
        listaCinemas.add(s.nomeCinema);
      }
    });
    return listaCinemas;
  }

}
