import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from 'src/app/helpers/auth.service';
import { User } from 'src/app/types/types';

@Component({
  selector: 'app-filmes-screen',
  templateUrl: './filmes-screen.component.html',
  styleUrls: ['./filmes-screen.component.css']
})
export class FilmesScreenComponent implements OnInit {

  user!: User | null;

  show: string = 'emCartaz';

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
  }

  changeEmCartaz = () => {
    if(this.show === 'emCartaz'){
      this.show = 'emBreve';
    } else {
      this.show = 'emCartaz'
    }
  }

}
