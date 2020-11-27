import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { USER_PATH } from '../utils/router';
import { LOGIN_PATH, PROFILE_PATH } from './utils/router';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.sass']
})
export class UserComponent implements OnInit {

  constructor(
    private authService: AuthService,
    private router: Router,
    public location: Location
  ) { }

  get login(): boolean{
    return this.router.url.includes(LOGIN_PATH);
  }

  get profile(): boolean{
    return this.router.url.includes(PROFILE_PATH);
  }

  signOut(): void{
    this.authService.deleteUser();
    this.router.navigate([`${USER_PATH}/${LOGIN_PATH}`]);
  }

  ngOnInit(): void {
  }

}
