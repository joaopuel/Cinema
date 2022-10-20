import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SessaoInfo } from 'src/app/types/types';

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
}
