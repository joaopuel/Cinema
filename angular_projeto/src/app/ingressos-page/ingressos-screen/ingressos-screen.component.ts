import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NonNullableFormBuilder } from '@angular/forms';
import { AuthenticationService } from 'src/app/helpers/auth.service';
import { Ingresso, User } from 'src/app/types/types';

@Component({
  selector: 'app-ingressos-screen',
  templateUrl: './ingressos-screen.component.html',
  styleUrls: ['./ingressos-screen.component.css']
})
export class IngressosScreenComponent implements OnInit {

  user!: User | null;

  listaIngressos: Ingresso[] = [];

  constructor(
    private http: HttpClient,
    private authenticationService: AuthenticationService
  ) {
    this.authenticationService.user.subscribe(x => this.user = x);
  }

  ngOnInit(): void {
    this.http.get<Ingresso[]>("/usuario/meusingressos").subscribe((ingressos) => this.listaIngressos = ingressos);
  }

  getData(data: Date){
    return new Date(data);
  }
}
