import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Login } from '../utils/login';
import { User } from 'src/app/utils/user';
import { API_HAS_EMAIL, API_LOGIN, API_REGISTER, API_UPDATE_PROFILE, API_ACTIVATION } from '../utils/api';
import { UniqueCheck } from 'src/app/utils/unique-check';
import { Registration } from '../utils/registration';

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

  hasEmail(param: UniqueCheck): Observable<boolean>{
    return this.http.post<boolean>(API_HAS_EMAIL, param);
  }

  register(registration: Registration): Observable<null>{
    return this.http.post<null>(API_REGISTER, registration);
  }

  activation(code: string): Observable<null>{
    return this.http.get<null>(`${API_ACTIVATION}/${code}`);
  }

  update(data: FormData): Observable<User>{
    return this.http.post<User>(API_UPDATE_PROFILE, data);
  }

}
