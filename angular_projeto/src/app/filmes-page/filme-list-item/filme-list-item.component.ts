import { AfterViewChecked, AfterViewInit, Component, DoCheck, Input, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Filme, Genero, GenerosFilmeDTO } from '../../types/types';
import { skipLast } from 'rxjs';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-filme-list-item',
  templateUrl: './filme-list-item.component.html',
  styleUrls: ['./filme-list-item.component.css']
})
export class FilmeListItemComponent implements OnInit {

  @Input() show!: string;

  listaFilmes: Filme[] = [];

  listaEmCartaz: Filme[] = [];

  listaEmBreve: Filme[] = [];

  listaGeneros: Genero[] = [];

  listaGenerosEscolhidos: string[] = [];

  listaNovosGeneros: Genero[] = [];

  filmeEscolhido!: Filme;

  filmeForm: FormGroup = this.formBuilder.group({
    nome: ['', Validators.required],
    duracao: ['', Validators.required],
    sinopse: ['', Validators.required],
    diretor: ['', Validators.required],
    cartaz: ['', Validators.required],
    trailer: ['', Validators.required]
  });

  generoForm: FormGroup = this.formBuilder.group({
    id: [null],
    nome: ['', Validators.required]
  });

  constructor(
    private http: HttpClient,
    private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    let url: string;
    if(this.show === 'todos'){
      url = "/filmes/meusfilmes";
    } else{
      url = "/filmes";
    }
    this.http.get<Filme[]>(url).subscribe((filmes) => {
      this.listaFilmes = filmes;
      this.listaEmCartaz = filmes.filter((f) => f.sessoes.some((s) => s.dataSessao.toString().split('T')[0] === (new Date().getFullYear() + '-' + (new Date().getMonth()+1) + '-' + new Date().getDate())));
      this.listaEmBreve = filmes.filter((f) => !(f.sessoes.some((s) => s.dataSessao.toString().split('T')[0] === (new Date().getFullYear() + '-' + (new Date().getMonth()+1) + '-' + new Date().getDate()))));
    }, (error) => {console.log(error)});
    this.http.get<Genero[]>("/generos").subscribe((generos) => {this.listaGeneros = generos}, (error) => {console.log(error)});
  }

  addGeneroEscolhido(g: Genero){
    if(this.listaGenerosEscolhidos.includes(g.nome)){
      this.listaGenerosEscolhidos.splice(this.listaGenerosEscolhidos.indexOf(g.nome), 1);
    } else{
      this.listaGenerosEscolhidos.push(g.nome);
    }
  }

  addGenero(){
    let g: Genero = (this.generoForm.value as Genero);
    this.listaNovosGeneros.push(g);
    this.listaGeneros.push(g);
  }

  addFilme(){
    let filme: Filme = (this.filmeForm.value as Filme);
    this.http.post<Filme>("/filmes", filme).subscribe((filme) => {

      let generos: number[] = [];

      this.listaGeneros.forEach((g) => {
        if(this.listaGenerosEscolhidos.includes(g.nome)){
          generos.push(g.id);
        }
      });

      if(this.listaNovosGeneros.length !== 0){
        this.http.post<Genero[]>("/generos/listageneros", this.listaNovosGeneros).subscribe(
          (newGeneros) => {
            newGeneros.forEach((g) => {
              if(this.listaGenerosEscolhidos.includes(g.nome)){
                generos.push(g.id);
              }
            });
            let generosFilme: GenerosFilmeDTO = {
              idFilme: filme.id,
              idGeneros: generos
            }
      
            this.http.post<GenerosFilmeDTO>("/filmes/generos", generosFilme).subscribe((sucess) => {window.location.reload()}, (error) => {console.log(error)});
          },
          (error) => {console.log(error)}
        )
      } else{

        let generosFilme: GenerosFilmeDTO = {
          idFilme: filme.id,
          idGeneros: generos
        }
  
        this.http.post<GenerosFilmeDTO>("/filmes/generos", generosFilme).subscribe((sucess) => {window.location.reload()}, (error) => {console.log(error)});

      }

    }, (error) => {console.log(error)});

  }

  setFilme(f: Filme){
    this.filmeEscolhido = f;
  }

  deleteFilme(){
    this.http.delete<any>(`/filmes/${this.filmeEscolhido.id}`).subscribe(success => {window.location.reload()}, error => {console.log(error)});
  }
}
