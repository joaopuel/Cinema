import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SessoesScreenComponent } from './sessoes-screen/sessoes-screen.component';
import { SessoesListItemComponent } from './sessoes-list-item/sessoes-list-item.component';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [
    SessoesScreenComponent,
    SessoesListItemComponent
  ],
  imports: [
    CommonModule, RouterModule
  ],
  exports: [
    SessoesListItemComponent
  ]
})
export class SessoesPageModule { }
