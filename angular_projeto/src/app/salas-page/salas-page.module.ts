import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SalasScreenComponent } from './salas-screen/salas-screen.component';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [
    SalasScreenComponent
  ],
  imports: [
    CommonModule, ReactiveFormsModule, RouterModule
  ],
  exports: [
    SalasScreenComponent
  ]
})
export class SalasPageModule { }
