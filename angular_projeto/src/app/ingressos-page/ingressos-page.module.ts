import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IngressosScreenComponent } from './ingressos-screen/ingressos-screen.component';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [
    IngressosScreenComponent
  ],
  imports: [
    CommonModule, RouterModule
  ],
  exports: [
    IngressosScreenComponent
  ]
})
export class IngressosPageModule { }
