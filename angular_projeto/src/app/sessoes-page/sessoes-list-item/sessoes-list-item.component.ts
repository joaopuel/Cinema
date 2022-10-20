import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FilmeInfo } from 'src/app/types/types';

@Component({
  selector: 'app-sessoes-list-item',
  templateUrl: './sessoes-list-item.component.html',
  styleUrls: ['./sessoes-list-item.component.css']
})
export class SessoesListItemComponent implements OnInit {

  @Input() dia!: Date;

  filme: FilmeInfo | undefined;

  nomeFilme: string | undefined | null;

  constructor(private acttivateRoute: ActivatedRoute, private http: HttpClient) {
  }

  ngOnInit(): void {
    this.nomeFilme = this.acttivateRoute.snapshot.paramMap.get('nome');
    this.http.get<FilmeInfo>(`/filmes/${this.nomeFilme}`).subscribe((filme) => this.filme = filme);
  }

  sessoesPorCinema(nomeCinema: String) {
    return this.filme?.sessoes.filter((ss) => (ss.nomeCinema === nomeCinema) && (ss.dataSessao.toString().split('T')[0] === this.dia.toISOString().split('T')[0]));
  }

  listaCinemas = () => {
    let listaCinemas: Set<string> = new Set();
    console.log("Dia escolhido: " + this.dia.getDate());
    this.filme?.sessoes.forEach((s) => {
      if(s.dataSessao.toString().split('T')[0] === this.dia.toISOString().split('T')[0]){
        listaCinemas.add(s.nomeCinema)
        console.log(s.dataSessao);
      }
    });
    console.log(listaCinemas);
    return listaCinemas;
  }

}
