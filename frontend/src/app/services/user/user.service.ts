import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Login } from 'src/app/models/login';
import { User } from 'src/app/models/user';
import { Registration } from 'src/app/models/registration';
import { UniqueCheck } from 'src/app/models/unique-check';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient
  ) { }

  private readonly API_AUTH: string = `${environment.baseUrl}/${environment.apiAuth}`;
  private readonly API_USER: string = `${environment.baseUrl}/${environment.apiUser}`;

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
    return this.http.post<{value: boolean}>(`${this.API_AUTH}/has_email`, param).pipe(
      map((response: {value: boolean}) => response.value)
    );
  }

}
