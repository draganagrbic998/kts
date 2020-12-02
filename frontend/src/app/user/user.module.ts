import { NgModule } from '@angular/core';

import { UserRoutingModule } from './user-routing.module';
import { LayoutModule } from '../layout/layout.module';
import { UserComponent } from './user.component';
import { AccountActivationComponent } from './account-activation/account-activation.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { RegisterFormComponent } from './register-form/register-form.component';
import { ProfileFormComponent } from './profile-form/profile-form.component';

@NgModule({
  declarations: [
    UserComponent,
    LoginFormComponent,
    RegisterFormComponent,
    ProfileFormComponent,
    AccountActivationComponent
  ],
  imports: [
    UserRoutingModule,
    LayoutModule
  ]
})
export class UserModule { }
