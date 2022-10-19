import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-sessao-screen',
  templateUrl: './sessao-screen.component.html',
  styleUrls: ['./sessao-screen.component.css']
})
export class SessaoScreenComponent implements OnInit {

  idSessao: number | undefined | null;

  constructor(private acttivateRoute: ActivatedRoute, private http: HttpClient) {
  }

  ngOnInit(): void {
    // this.idSessao = this.acttivateRoute.snapshot.paramMap.get('id');
    // this.http.get<FilmeInfo>(`/filmes/${this.nomeFilme}`).subscribe((filme) => this.filme = filme);
  }

}
