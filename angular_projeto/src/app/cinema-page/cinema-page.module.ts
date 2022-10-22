import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CinemaScreenComponent } from './cinema-screen/cinema-screen.component';
import { RouterModule } from '@angular/router';
import { CinemaSessoesListItemComponent } from './cinema-sessoes-list-item/cinema-sessoes-list-item.component';



@NgModule({
  declarations: [
    CinemaScreenComponent,
    CinemaSessoesListItemComponent
  ],
  imports: [
    CommonModule, RouterModule
  ],
  exports: [
    CinemaScreenComponent
  ]
})
export class CinemaPageModule { }
