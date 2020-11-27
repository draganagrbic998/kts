import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Login } from '../utils/login';
import { User } from 'src/app/utils/user';
import { API_HAS_EMAIL, API_LOGIN, API_REGISTER, API_UPDATE, API_ACTIVATE } from '../utils/api';
import { UniqueCheck } from 'src/app/utils/unique-check';
import { Registration } from '../utils/registration';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient
  ) { }

  login(login: Login): Observable<User>{
    return this.http.post<User>(API_LOGIN, login);
  }

  register(register: Registration): Observable<null>{
    return this.http.post<null>(API_REGISTER, register);
  }

  activate(code: string): Observable<null>{
    return this.http.get<null>(`${API_ACTIVATE}/${code}`);
  }

  update(data: FormData): Observable<User>{
    return this.http.post<User>(API_UPDATE, data);
  }
  
  hasEmail(param: UniqueCheck): Observable<boolean>{
    return this.http.post<boolean>(API_HAS_EMAIL, param);
  }

}
