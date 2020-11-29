import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Type } from '../utils/type';
import { API_BASE } from '../utils/api';
import { MEDIUM_PAGE_SIZE } from 'src/app/utils/constants';

@Injectable({
  providedIn: 'root'
})
export class TypeService {

  constructor(private http: HttpClient
    ) { }

 

  list(page: number): Observable<HttpResponse<Type[]>>{
    return this.http.get<Type[]>(`${API_BASE}?page=${page}&size=${MEDIUM_PAGE_SIZE}`, 
     { observe: 'response' }
    );
  }

  save(type: Type): Observable<null>{
    return this.http.post<null>(API_BASE, type);
  }

  delete(id: number): Observable<null>{
    return this.http.delete<null>(`${API_BASE}/${id}`);
  }
  
}
