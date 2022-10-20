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
