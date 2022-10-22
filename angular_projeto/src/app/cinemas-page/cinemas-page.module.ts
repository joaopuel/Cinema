import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CinemasScreenComponent } from './cinemas-screen/cinemas-screen.component';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    CinemasScreenComponent
  ],
  imports: [
    CommonModule, RouterModule, ReactiveFormsModule
  ],
  exports: [
    CinemasScreenComponent
  ]
})
export class CinemasPageModule { }
