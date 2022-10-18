import { LoginComponent } from './login/login.component';
import { AuthGuardService } from './services/auth-guard.service';
import { MesaComponent } from './mesa/mesa.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', component: MesaComponent, canActivate: [AuthGuardService] },
  {
    path: 'login',
    component: LoginComponent,
  },
  { path: '**', redirectTo: '' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
