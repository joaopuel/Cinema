import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FilmesScreenComponent } from './filmes-screen/filmes-screen.component';
import { FilmeListItemComponent } from './filme-list-item/filme-list-item.component';
import { HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [
    FilmesScreenComponent,
    FilmeListItemComponent
  ],
  imports: [
    CommonModule, HttpClientModule
  ], exports: [
    FilmesScreenComponent
  ]
})
export class FilmesModule { }
