import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from 'src/app/helpers/auth.service';
import { CinemaInfo, SalaPayLoad, User } from 'src/app/types/types';

@Component({
  selector: 'app-salas-screen',
  templateUrl: './salas-screen.component.html',
  styleUrls: ['./salas-screen.component.css']
})
export class SalasScreenComponent implements OnInit {

  user!: User | null;

  cinema!: CinemaInfo | null;

  idCinema!: string | null;

  salaForm: FormGroup = this.formBuilder.group({
    id: [null],
    nome: ['', Validators.required],
    idCinema: [this.idCinema]
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
    this.idCinema = this.acttivateRoute.snapshot.paramMap.get('id');
    this.http.get<CinemaInfo>(`/cinemas/${this.idCinema}`).subscribe((cinema) => this.cinema = cinema);
  }

  addSala(){
    var sala: SalaPayLoad = (this.salaForm.value as SalaPayLoad);
    this.http.post<any>("/salas", sala).subscribe(success => {console.log(success)}, error => {console.log(error)});
    window.location.reload();
  }

}
