import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CadeiraInfo, SessaoInfo } from 'src/app/types/types';

@Component({
  selector: 'app-sessao-screen',
  templateUrl: './sessao-screen.component.html',
  styleUrls: ['./sessao-screen.component.css']
})
export class SessaoScreenComponent implements OnInit {

  idSessao!: string | null;

  sessao!: SessaoInfo;

  constructor(private acttivateRoute: ActivatedRoute, private http: HttpClient) {
  }

  ngOnInit(): void {
    this.idSessao = this.acttivateRoute.snapshot.paramMap.get('id');
    this.http.get<SessaoInfo>(`/sessoes/${this.idSessao}`).subscribe((sessao) => this.sessao = sessao);
  }

  getCadeirasByFileira = (fileira: number) => {
    let listaCadeiras: (CadeiraInfo | undefined)[] = [];
    for(let i=1; i <= this.sessao.sala.tamFileiras; i++){
      listaCadeiras.push(this.sessao.sala.cadeiras.filter((c) => c.fileira === fileira).find((c) => c.ordemFileira === i));
    }
    return listaCadeiras;
  }

  getFileiras = () => {
    return this.range(this.sessao.sala.numFileiras, 1);
  }

  range(size: number, startAt: number) {
    return [...Array(size).keys()].map(i => i + startAt).reverse();
  }
}
