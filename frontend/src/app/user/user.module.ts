import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { LayoutModule } from '../layout/layout.module';
import { UserComponent } from './user.component';


@NgModule({
  declarations: [LoginFormComponent, UserComponent],
  imports: [
    CommonModule,
    UserRoutingModule,
    LayoutModule
  ]
})
export class UserModule { }
