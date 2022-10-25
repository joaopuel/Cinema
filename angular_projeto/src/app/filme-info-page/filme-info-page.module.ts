import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FilmeInfoScreenComponent } from './filme-info-screen/filme-info-screen.component';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    FilmeInfoScreenComponent
  ],
  imports: [
    CommonModule, RouterModule, ReactiveFormsModule
  ],
  exports: [
    FilmeInfoScreenComponent
  ]
})
export class FilmeInfoPageModule { }
