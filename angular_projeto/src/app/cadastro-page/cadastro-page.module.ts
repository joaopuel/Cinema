import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CadastroScreenComponent } from './cadastro-screen/cadastro-screen.component';
import { ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    CadastroScreenComponent
  ],
  imports: [
    CommonModule, ReactiveFormsModule
  ],
  exports: [
    CadastroScreenComponent
  ]
})
export class CadastroPageModule { }
