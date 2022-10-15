import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Filme } from '../../types/types';

@Component({
  selector: 'app-filme-list-item',
  templateUrl: './filme-list-item.component.html',
  styleUrls: ['./filme-list-item.component.css']
})
export class FilmeListItemComponent implements OnInit {

  listaFilmes: Filme[] = [];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get<Filme[]>("/filmes").subscribe((filmes) => this.listaFilmes = filmes);
  }
}
