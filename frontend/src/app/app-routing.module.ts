import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { USER_PATH } from './utils/router';

const routes: Routes = [
  {
    path: USER_PATH,
    loadChildren: () => import('./user/user.module').then(m => m.UserModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
