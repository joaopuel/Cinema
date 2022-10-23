import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FilmesScreenComponent } from './filmes-screen/filmes-screen.component';
import { FilmeListItemComponent } from './filme-list-item/filme-list-item.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    FilmesScreenComponent,
    FilmeListItemComponent
  ],
  imports: [
    CommonModule, HttpClientModule, RouterModule, ReactiveFormsModule
  ], exports: [
    FilmesScreenComponent
  ]
})
export class FilmesModule { }
