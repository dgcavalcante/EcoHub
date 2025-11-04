import { Routes } from '@angular/router';
import { LandingPage } from './pages/landingpage/landingpage';
import { Login } from './pages/login/login';
import { Cadastro } from './pages/cadastro/cadastro';

export const routes: Routes = [
  { path: '', redirectTo: 'landingpage', pathMatch: 'full' },
  { path: 'landingpage', component: LandingPage },
  { path: 'login', component: Login },
  { path: 'cadastro', component: Cadastro },
];