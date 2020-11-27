import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HOME_PATH } from '../utils/router';
import { AccountActivationComponent } from './account-activation/account-activation.component';
import { GuestGuard } from './guard/guest.guard';
import { LoginFormComponent } from './login-form/login-form.component';
import { ProfileFormComponent } from './profile-form/profile-form.component';
import { RegisterFormComponent } from './register-form/register-form.component';
import { UserComponent } from './user.component';
import { LOGIN_PATH, PROFILE_PATH, REGISTER_PATH, ACTIVATE_PATH } from './utils/router';

const routes: Routes = [
  {
    path: HOME_PATH,
    component: UserComponent,
    children: [
      {
        path: LOGIN_PATH,
        component: LoginFormComponent
      }, 
      {
        path: REGISTER_PATH,
        component: RegisterFormComponent
      },
      {
        path: PROFILE_PATH,
        component: ProfileFormComponent, 
        canActivate: [GuestGuard]
      }
    ]
  }, 
  {
    path: ACTIVATE_PATH,
    component: AccountActivationComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
