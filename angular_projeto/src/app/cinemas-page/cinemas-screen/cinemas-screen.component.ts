import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/helpers/auth.service';
import { Cinema, User } from 'src/app/types/types';

@Component({
  selector: 'app-cinemas-screen',
  templateUrl: './cinemas-screen.component.html',
  styleUrls: ['./cinemas-screen.component.css']
})
export class CinemasScreenComponent implements OnInit {

  user!: User | null;

  listaCinemas: Cinema[] = [];

  constructor(
    private http: HttpClient,
    private authenticationService: AuthenticationService
  ) {
    this.authenticationService.user.subscribe(x => this.user = x);
  }

  ngOnInit(): void {
    this.http.get<Cinema[]>("/cinemas").subscribe((cinemas) => this.listaCinemas = cinemas);
  }

}
