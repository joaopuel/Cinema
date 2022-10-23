import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from 'src/app/helpers/auth.service';
import { CinemaInfo, Sala, User } from 'src/app/types/types';

@Component({
  selector: 'app-salas-screen',
  templateUrl: './salas-screen.component.html',
  styleUrls: ['./salas-screen.component.css']
})
export class SalasScreenComponent implements OnInit {

  user!: User | null;

  cinema!: CinemaInfo | null;

  id!: string | null;

  salaEscolhida!: Sala;

  salaForm: FormGroup = this.formBuilder.group({
    nome: ['', Validators.required]
  });


  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private authenticationService: AuthenticationService,
    private router: Router,
    private acttivateRoute: ActivatedRoute
  ) {
    this.authenticationService.user.subscribe(x => this.user = x);
  }

  ngOnInit(): void {
    this.id = this.acttivateRoute.snapshot.paramMap.get('id');
    this.http.get<CinemaInfo>(`/cinemas/${this.id}`).subscribe((cinema) => this.cinema = cinema);
  }

  addSala(){
    let nome = (this.salaForm.value.nome);
    let idCinema = this.cinema!.id;
    this.http.post<any>("/salas", { nome,  idCinema}).subscribe(success => {window.location.reload()}, error => {console.log(error)});
  }

  setSala(s: Sala){
    this.salaEscolhida = s;
  }

  deleteSala(){
    this.http.delete<any>(`/salas/${this.salaEscolhida.id}`).subscribe(success => {window.location.reload()}, error => {console.log(error)});
  }

}
