import { NgModule } from '@angular/core';

import { UserRoutingModule } from './user-routing.module';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { LayoutModule } from '../layout/layout.module';
import { UserComponent } from './user.component';
import { ProfileFormComponent } from './components/profile-form/profile-form.component';
import { ProfileDetailsComponent } from './components/profile-details/profile-details.component';
import { RegistrationFormComponent } from './registration-form/registration-form.component';


@NgModule({
  declarations: [LoginFormComponent, UserComponent, ProfileFormComponent, ProfileDetailsComponent, RegistrationFormComponent],
  imports: [
    UserRoutingModule,
    LayoutModule
  ], 
  exports: [
    ProfileDetailsComponent
  ]
})
export class UserModule { }
