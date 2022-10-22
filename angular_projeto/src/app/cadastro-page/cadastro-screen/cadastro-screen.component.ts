import { HttpClient } from '@angular/common/http';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PessoaPayloadDTO } from 'src/app/types/types';

@Component({
  selector: 'app-cadastro-screen',
  templateUrl: './cadastro-screen.component.html',
  styleUrls: ['./cadastro-screen.component.css']
})
export class CadastroScreenComponent implements OnInit {

  @Output() cadastrar = new EventEmitter<PessoaPayloadDTO>();

  cadastroForm: FormGroup = this.formBuilder.group({
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
    var cadastro: PessoaPayloadDTO = (this.cadastroForm.value as PessoaPayloadDTO);
    this.http.post<PessoaPayloadDTO>("/usuario/criarusuario", cadastro).subscribe(success => {console.log(success)}, error => {console.log(error)});
  }

}
