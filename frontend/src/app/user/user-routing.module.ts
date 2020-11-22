import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { ProfileFormComponent } from './components/profile-form/profile-form.component';
import { RegistrationFormComponent } from './registration-form/registration-form.component';
import { UserComponent } from './user.component';
import { LOGIN_PATH, PROFILE_PATH, REGISTER_PATH } from './utils/router';

const routes: Routes = [
  {
    component: UserComponent,
    path: "",
    children: [
      {
        path: LOGIN_PATH,
        component: LoginFormComponent
      }, 
      {
        path: PROFILE_PATH,
        component: ProfileFormComponent
      },
      {
        path: REGISTER_PATH,
        component: RegistrationFormComponent
      },/*
      {
        path: "**", 
        pathMatch: 'full',
        redirectTo: LOGIN_PATH 
      }*/
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
