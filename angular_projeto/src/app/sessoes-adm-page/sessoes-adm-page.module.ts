import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SessoesAdmScreenComponent } from './sessoes-adm-screen/sessoes-adm-screen.component';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [
    SessoesAdmScreenComponent
  ],
  imports: [
    CommonModule, ReactiveFormsModule, RouterModule

  ],
  exports: [
    SessoesAdmScreenComponent
  ]
})
export class SessoesAdmPageModule { }
