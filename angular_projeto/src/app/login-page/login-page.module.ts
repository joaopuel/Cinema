import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginScreenComponent } from './login-screen/login-screen.component';
import { ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    LoginScreenComponent
  ],
  imports: [
    CommonModule, ReactiveFormsModule
  ],
  exports: [
    LoginScreenComponent
  ]
})
export class LoginPageModule { }
