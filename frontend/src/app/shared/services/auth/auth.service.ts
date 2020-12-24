import { Injectable } from '@angular/core';
import { User } from 'src/app/models/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  private readonly USER_KEY = 'user';

  saveUser(profile: User): void{
    localStorage.setItem(this.USER_KEY, JSON.stringify(profile));
  }

  deleteUser(): void{
    localStorage.removeItem(this.USER_KEY);
  }

  getUser(): User{
    return JSON.parse(localStorage.getItem(this.USER_KEY));
  }

}
