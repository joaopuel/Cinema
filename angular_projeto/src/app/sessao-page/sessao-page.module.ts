import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SessaoScreenComponent } from './sessao-screen/sessao-screen.component';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [
    SessaoScreenComponent
  ],
  imports: [
    CommonModule, RouterModule
  ],
  exports: [
    SessaoScreenComponent
  ]
})
export class SessaoPageModule { }
