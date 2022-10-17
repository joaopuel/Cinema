import { AfterViewChecked, AfterViewInit, Component, DoCheck, Input, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Filme } from '../../types/types';
import { skipLast } from 'rxjs';

@Component({
  selector: 'app-filme-list-item',
  templateUrl: './filme-list-item.component.html',
  styleUrls: ['./filme-list-item.component.css']
})
export class FilmeListItemComponent implements OnInit {

  @Input() emCartaz!: boolean;

  listaFilmes: Filme[] = [];

  listaEmCartaz: Filme[] = [];

  listaEmBreve: Filme[] = [];

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    this.http.get<Filme[]>("/filmes").subscribe((filmes) => {
      this.listaEmCartaz = filmes.filter((f) => f.sessoes.some((s) => s.dataSessao.toString().split('T')[0] === new Date().toISOString().split('T')[0]));
      this.listaEmBreve = filmes.filter((f) => !(f.sessoes.some((s) => s.dataSessao.toString().split('T')[0] === new Date().toISOString().split('T')[0])));
    });
  }
}
