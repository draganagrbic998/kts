import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';
import { GUEST_ROLE } from 'src/app/utils/constants';
import { USER_PATH } from 'src/app/utils/router';
import { LOGIN_PATH } from '../utils/router';

@Injectable({
  providedIn: 'root'
})
export class GuestGuard implements CanActivate {

  constructor(
    private authService: AuthService,
    private router: Router
  ){}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if (this.authService.getUser()?.role !== GUEST_ROLE){
      this.router.navigate([`${USER_PATH}/${LOGIN_PATH}`]);
      return false;
    }
    return true;
  }

}
