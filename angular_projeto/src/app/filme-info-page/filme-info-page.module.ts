import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FilmeInfoScreenComponent } from './filme-info-screen/filme-info-screen.component';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [
    FilmeInfoScreenComponent
  ],
  imports: [
    CommonModule, RouterModule
  ],
  exports: [
    FilmeInfoScreenComponent
  ]
})
export class FilmeInfoPageModule { }
