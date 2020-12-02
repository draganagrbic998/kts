import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Login } from '../utils/login';
import { User } from 'src/app/utils/user';
import { UniqueCheck } from 'src/app/utils/unique-check';
import { Registration } from '../utils/registration';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient
  ) { }

  private readonly API_AUTH: string = `${environment.baseUrl}/auth`;
  private readonly API_USER: string = `${environment.baseUrl}/api/user`;

  login(login: Login): Observable<User>{
    return this.http.post<User>(`${this.API_AUTH}/login`, login);
  }

  register(register: Registration): Observable<null>{
    return this.http.post<null>(`${this.API_AUTH}/register`, register);
  }

  activate(code: string): Observable<null>{
    return this.http.get<null>(`${this.API_AUTH}/activate/${code}`);
  }

  update(data: FormData): Observable<User>{
    return this.http.post<User>(this.API_USER, data);
  }

  hasEmail(param: UniqueCheck): Observable<boolean>{
    return this.http.post<boolean>(`${this.API_AUTH}/has_email`, param);
  }

}
