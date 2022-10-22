import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SalaScreenComponent } from './sala-screen/sala-screen.component';



@NgModule({
  declarations: [
    SalaScreenComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    SalaScreenComponent
  ]
})
export class SalaPageModule { }
