import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BasicAuthInterceptor } from './helpers/basic-auth.interceptor';
import { ReactiveFormsModule } from '@angular/forms';

import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { FilmesModule } from './filmes-page/filmes.module';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { LoginPageModule } from './login-page/login-page.module';
import { FilmeInfoPageModule } from './filme-info-page/filme-info-page.module';
import { LandingPageCardsComponent } from './landing-page/landing-page-cards/landing-page-cards.component';
import { LandingPageScreenComponent } from './landing-page/landing-page-screen/landing-page-screen.component';
import { LandingPageModule } from './landing-page/landing-page.module';
import { SessoesPageModule } from './sessoes-page/sessoes-page.module';
import { SessaoPageModule } from './sessao-page/sessao-page.module';
import { SobrePageModule } from './sobre-page/sobre-page.module';
import { CadastroPageModule } from './cadastro-page/cadastro-page.module';
import { IngressosPageModule } from './ingressos-page/ingressos-page.module';
import { CinemasPageModule } from './cinemas-page/cinemas-page.module';
import { CinemaPageModule } from './cinema-page/cinema-page.module';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule, 
    AppRoutingModule, 
    FilmesModule, 
    HttpClientModule, 
    ReactiveFormsModule, 
    LoginPageModule, 
    FilmeInfoPageModule, 
    LandingPageModule,
    SessoesPageModule,
    SessaoPageModule,
    SobrePageModule,
    CadastroPageModule,
    IngressosPageModule,
    CinemasPageModule,
    CinemaPageModule
  ],
  exports: [
    FooterComponent
  ],
  providers: [
    {provide: LocationStrategy, useClass: HashLocationStrategy},
    { provide: HTTP_INTERCEPTORS, useClass: BasicAuthInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
