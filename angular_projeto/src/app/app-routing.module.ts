import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { CadastroScreenComponent } from "./cadastro-page/cadastro-screen/cadastro-screen.component";
import { CinemaScreenComponent } from "./cinema-page/cinema-screen/cinema-screen.component";
import { CinemasScreenComponent } from "./cinemas-page/cinemas-screen/cinemas-screen.component";
import { FilmeInfoScreenComponent } from "./filme-info-page/filme-info-screen/filme-info-screen.component";
import { FilmesScreenComponent } from "./filmes-page/filmes-screen/filmes-screen.component";
import { AuthGuard } from './helpers/auth.guard';
import { IngressosScreenComponent } from "./ingressos-page/ingressos-screen/ingressos-screen.component";
import { LandingPageScreenComponent } from "./landing-page/landing-page-screen/landing-page-screen.component";
import { LoginScreenComponent } from "./login-page/login-screen/login-screen.component";
import { SalaScreenComponent } from "./sala-page/sala-screen/sala-screen.component";
import { SalasScreenComponent } from "./salas-page/salas-screen/salas-screen.component";
import { SessaoScreenComponent } from "./sessao-page/sessao-screen/sessao-screen.component";
import { SessoesAdmScreenComponent } from "./sessoes-adm-page/sessoes-adm-screen/sessoes-adm-screen.component";
import { SessoesScreenComponent } from "./sessoes-page/sessoes-screen/sessoes-screen.component";
import { SobreScreenComponent } from "./sobre-page/sobre-screen/sobre-screen.component";

const routes: Routes = [
    { path: '', component: FilmesScreenComponent },
    { path: 'login', component: LoginScreenComponent },
    { path: 'cadastro', component:  CadastroScreenComponent},
    { path: 'filmes', component: FilmesScreenComponent },
    { path: 'filme/:nome', component: FilmeInfoScreenComponent },
    { path: 'filme/:nome/sessoes', component:  SessoesScreenComponent},
    { path: 'sessao/:id', component:  SessaoScreenComponent},
    { path: 'quemsomos', component:  SobreScreenComponent},
    { path: 'meusingressos', component: IngressosScreenComponent, canActivate: [AuthGuard] },
    { path: 'cinemas', component: CinemasScreenComponent},
    { path: 'cinema/:id', component: CinemaScreenComponent},
    { path: 'cinema/:id/salas', component: SalasScreenComponent, canActivate: [AuthGuard]},
    { path: 'sala/:id', component: SalaScreenComponent, canActivate: [AuthGuard]},
    { path: 'sessoes/filme/:nome', component: SessoesAdmScreenComponent, canActivate: [AuthGuard]},
    { path: '**', redirectTo: '/'}
  ];
  

@NgModule({
    imports: [
        RouterModule.forRoot(routes)
    ],
    exports: [
        RouterModule
    ]
})

export class AppRoutingModule {}