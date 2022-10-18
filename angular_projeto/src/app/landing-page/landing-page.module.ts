import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LandingPageScreenComponent } from './landing-page-screen/landing-page-screen.component';
import { LandingPageCardsComponent } from './landing-page-cards/landing-page-cards.component';
import { NgImageSliderModule } from 'ng-image-slider';

@NgModule({
  declarations: [
    LandingPageCardsComponent,
    LandingPageScreenComponent
  ],
  imports: [
    CommonModule,
    NgImageSliderModule
  ],
  exports: [
    LandingPageScreenComponent
  ]
})
export class LandingPageModule { }
