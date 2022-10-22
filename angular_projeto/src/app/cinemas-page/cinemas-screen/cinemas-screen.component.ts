import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/helpers/auth.service';
import { Cinema, CinemaInfo, User } from 'src/app/types/types';

@Component({
  selector: 'app-cinemas-screen',
  templateUrl: './cinemas-screen.component.html',
  styleUrls: ['./cinemas-screen.component.css']
})
export class CinemasScreenComponent implements OnInit {

  user!: User | null;

  listaCinemas: Cinema[] = [];
  listaCinemasInfo: CinemaInfo[] = [];

  cinemaForm: FormGroup = this.formBuilder.group({
    id: [null],
    nome: ['', Validators.required],
    logradouro: ['', Validators.required],
    numero: ['', Validators.required]
  });


  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private authenticationService: AuthenticationService,
    private router: Router
  ) {
    this.authenticationService.user.subscribe(x => this.user = x);
  }

  ngOnInit(): void {
    if(this.user == null || this.user.login !== 'admin'){
      this.http.get<Cinema[]>("/cinemas").subscribe((cinemas) => this.listaCinemas = cinemas);
    } else {
      this.http.get<CinemaInfo[]>("/usuario/meuscinemas").subscribe((cinemas) => this.listaCinemasInfo = cinemas);
    }
  }

  getTotal(){
    let valor: number = 0;
    this.listaCinemasInfo.forEach((c) => valor = valor + c.caixa);
    return valor;
  }

  addCinema(){
    var cinema: Cinema = (this.cinemaForm.value as Cinema);
    this.http.post<Cinema>("/cinemas", cinema).subscribe(success => {console.log(success)}, error => {console.log(error)});
    window.location.reload();
  }

}
