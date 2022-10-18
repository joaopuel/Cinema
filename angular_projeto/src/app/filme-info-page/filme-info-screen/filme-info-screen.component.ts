import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { FilmeInfo } from 'src/app/types/types';

@Component({
  selector: 'app-filme-info-screen',
  templateUrl: './filme-info-screen.component.html',
  styleUrls: ['./filme-info-screen.component.css']
})
export class FilmeInfoScreenComponent implements OnInit {

  @Input() nomeFilme!: string;

  filme: FilmeInfo | undefined;

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    this.http.get<FilmeInfo>(`/filmes/${this.nomeFilme}`).subscribe((filme) => this.filme = filme);
  }

}
