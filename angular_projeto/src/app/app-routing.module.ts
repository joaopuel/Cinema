import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { CadastroScreenComponent } from "./cadastro-page/cadastro-screen/cadastro-screen.component";
import { FilmeInfoScreenComponent } from "./filme-info-page/filme-info-screen/filme-info-screen.component";
import { FilmesScreenComponent } from "./filmes-page/filmes-screen/filmes-screen.component";
import { AuthGuard } from './helpers/auth.guard';
import { LandingPageScreenComponent } from "./landing-page/landing-page-screen/landing-page-screen.component";
import { LoginScreenComponent } from "./login-page/login-screen/login-screen.component";
import { SessaoScreenComponent } from "./sessao-page/sessao-screen/sessao-screen.component";
import { SessoesScreenComponent } from "./sessoes-page/sessoes-screen/sessoes-screen.component";
import { SobreScreenComponent } from "./sobre-page/sobre-screen/sobre-screen.component";

const routes: Routes = [
    { path: '', component: FilmesScreenComponent },
    { path: 'login', component: LoginScreenComponent },
    { path: 'cadastro', component:  CadastroScreenComponent},
    { path: 'filmes', component: FilmesScreenComponent },
    { path: 'filmes/filme/:nome', component: FilmeInfoScreenComponent },
    { path: 'filmes/filme/:nome/sessoes', component:  SessoesScreenComponent},
    { path: 'sessao/:id', component:  SessaoScreenComponent},
    { path: 'quemsomos', component:  SobreScreenComponent},
    // { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard] },
    // { path: 'dashboard/franquias', component: FranquiasScreenComponent, canActivate: [AuthGuard] },
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