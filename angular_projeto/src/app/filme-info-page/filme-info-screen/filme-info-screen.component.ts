import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FilmeInfo } from 'src/app/types/types';

@Component({
  selector: 'app-filme-info-screen',
  templateUrl: './filme-info-screen.component.html',
  styleUrls: ['./filme-info-screen.component.css']
})
export class FilmeInfoScreenComponent implements OnInit {

  filme: FilmeInfo | undefined;

  nomeFilme: string | undefined | null;

  constructor(private acttivateRoute: ActivatedRoute, private http: HttpClient) {
  }

  ngOnInit(): void {
    this.nomeFilme = this.acttivateRoute.snapshot.paramMap.get('nome');
    this.http.get<FilmeInfo>(`/filmes/${this.nomeFilme}`).subscribe((filme) => this.filme = filme);
  }


}
