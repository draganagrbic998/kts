import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Type } from '../utils/Type';
import {  API_DELETE, API_LIST,API_ADD } from '../utils/api';

@Injectable({
  providedIn: 'root'
})
export class TypeService {

  constructor(private http: HttpClient
    ) { }

  getAllTypes(): Observable<Type[]>{
    return this.http.get<Type[]>(API_LIST);
  }

  addCategory(cat: Type): Observable<null>{
    return this.http.post<null>(API_ADD,cat);
  }

  delete(id: number): Observable<null>{
    return this.http.delete<null>(`${API_DELETE}${id}`);
  }

  
}
