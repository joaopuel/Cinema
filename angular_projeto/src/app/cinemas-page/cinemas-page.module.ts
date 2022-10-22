import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CinemasScreenComponent } from './cinemas-screen/cinemas-screen.component';



@NgModule({
  declarations: [
    CinemasScreenComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    CinemasScreenComponent
  ]
})
export class CinemasPageModule { }
