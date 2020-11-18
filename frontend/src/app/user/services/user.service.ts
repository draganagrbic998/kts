import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Login } from '../utils/login';
import { User } from 'src/app/utils/user';
import { API_LOGIN } from '../utils/api';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient
  ) { }

  signIn(login: Login): Observable<User>{
    return this.http.post<User>(API_LOGIN, login);
  }

  

}
