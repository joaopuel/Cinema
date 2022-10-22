import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Filme } from 'src/app/types/types';

@Component({
  selector: 'app-cinema-sessoes-list-item',
  templateUrl: './cinema-sessoes-list-item.component.html',
  styleUrls: ['./cinema-sessoes-list-item.component.css']
})
export class CinemaSessoesListItemComponent implements OnInit {

  @Input() dia!: Date;

  listaFilmes: Filme[] = [];

  id!: string | null;

  constructor(private acttivateRoute: ActivatedRoute, private http: HttpClient) {
  }

  ngOnInit(): void {
    this.id = this.acttivateRoute.snapshot.paramMap.get('id');
    this.http.get<Filme[]>(`/filmes/cinema/${this.id}`).subscribe((filmes) => this.listaFilmes = filmes);
  }

  sessoesPorFilme(id: number) {
    return this.listaFilmes.find((f) => f.id === id)?.sessoes.filter((ss) => (ss.dataSessao.toString().split('T')[0] === (this.dia.getFullYear() + '-' + (this.dia.getMonth()+1) + '-' + this.dia.getDate())));
  }

  filmesEmCartaz = () => {
    return this.listaFilmes.filter((f) => f.sessoes.some((ss) => (ss.dataSessao.toString().split('T')[0] === (this.dia.getFullYear() + '-' + (this.dia.getMonth()+1) + '-' + this.dia.getDate()))));
  }

}
