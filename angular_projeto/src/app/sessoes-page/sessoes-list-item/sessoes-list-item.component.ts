import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FilmeInfo } from 'src/app/types/types';

@Component({
  selector: 'app-sessoes-list-item',
  templateUrl: './sessoes-list-item.component.html',
  styleUrls: ['./sessoes-list-item.component.css']
})
export class SessoesListItemComponent implements OnInit {

  filme: FilmeInfo | undefined;

  nomeFilme: string | undefined | null;

  listaCinemas: Set<string> = new Set();

  constructor(private acttivateRoute: ActivatedRoute, private http: HttpClient) {
  }

  ngOnInit(): void {
    this.nomeFilme = this.acttivateRoute.snapshot.paramMap.get('nome');
    this.http.get<FilmeInfo>(`/filmes/${this.nomeFilme}`).subscribe((filme) => {
      this.filme = filme;
      filme.sessoes.forEach((s) => this.listaCinemas.add(s.nomeCinema));
    });
  }

  sessoesPorCinema(nomeCinema: String) {
    return this.filme?.sessoes.filter((ss) => ss.nomeCinema === nomeCinema);
  }

}
