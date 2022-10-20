import { HttpClient } from '@angular/common/http';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Cadastro } from 'src/app/types/types';

@Component({
  selector: 'app-cadastro-screen',
  templateUrl: './cadastro-screen.component.html',
  styleUrls: ['./cadastro-screen.component.css']
})
export class CadastroScreenComponent implements OnInit {

  @Output() cadastrar = new EventEmitter<Cadastro>();

  cadastroForm: FormGroup = this.formBuilder.group({
    id: [null],
    nome: ['', Validators.required],
    sobrenome: ['', Validators.required],
    telefone: ['', Validators.required],
    cpf: ['', Validators.required],
    login: ['', Validators.required],
    senha: ['', Validators.required]
  });

  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient
    ) { }

  ngOnInit(): void {
  }

  onSubmit = () => {
    let cadastro: Cadastro = (this.cadastroForm.value as Cadastro);
    this.http.post<Cadastro>("/usuarios/criarusuario", {cadastro});
  }

}
