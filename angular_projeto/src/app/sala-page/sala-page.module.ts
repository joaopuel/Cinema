import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SalaScreenComponent } from './sala-screen/sala-screen.component';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    SalaScreenComponent
  ],
  imports: [
    CommonModule, RouterModule, ReactiveFormsModule
  ],
  exports: [
    SalaScreenComponent
  ]
})
export class SalaPageModule { }
