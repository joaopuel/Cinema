import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AuthenticationService } from 'src/app/helpers/auth.service';
import { AvaliacaoPayload, FilmeInfo, User } from 'src/app/types/types';

@Component({
  selector: 'app-filme-info-screen',
  templateUrl: './filme-info-screen.component.html',
  styleUrls: ['./filme-info-screen.component.css']
})
export class FilmeInfoScreenComponent implements OnInit {

  user: User | null = null;

  filme: FilmeInfo | undefined;

  nomeFilme: string | undefined | null;

  nota: number = 0;

  avaliacaoForm: FormGroup = this.formBuilder.group({
    comentario: ['']
  });

  constructor(
    private acttivateRoute: ActivatedRoute, 
    private http: HttpClient,
    private authenticationService: AuthenticationService,
    private formBuilder: FormBuilder) {
      this.authenticationService.user.subscribe((x) => this.user = x);
  }

  ngOnInit(): void {
    this.nomeFilme = this.acttivateRoute.snapshot.paramMap.get('nome');
    this.http.get<FilmeInfo>(`/filmes/${this.nomeFilme}`).subscribe((filme) => this.filme = filme);
  }

  getMyDate(d: Date){
    return new Date(d);
  }

  setNota(n: number){
    this.nota = n;
  }

  saveAvaliacao(){
    let avaliacao: AvaliacaoPayload = {
      idFilme: this.filme!.id,
      nota: this.nota,
      comentario: (this.avaliacaoForm.value.comentario === '') ? null : this.avaliacaoForm.value.comentario
    }
    this.http.post<AvaliacaoPayload>("/avaliacoes", avaliacao).subscribe(
      (sucess) => {
        window.location.reload();
      },
      (error) => {
        console.log(error);
      }
    );
  }

}
