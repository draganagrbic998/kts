import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Type } from '../utils/type';
import { API_BASE } from '../utils/api';

@Injectable({
  providedIn: 'root'
})
export class TypeService {

  constructor(private http: HttpClient
    ) { }

  list(): Observable<Type[]>{
    return this.http.get<Type[]>(API_BASE);
  }

  save(type: Type): Observable<null>{
    return this.http.post<null>(API_BASE, type);
  }

  delete(id: number): Observable<null>{
    return this.http.delete<null>(`${API_BASE}${id}`);
  }
  
}
