import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { GUEST_ROLE } from 'src/app/constants/roles';
import { LOGIN_PATH, USER_PATH } from 'src/app/constants/router';
import { AuthService } from 'src/app/services/auth/auth.service';

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
