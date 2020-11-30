import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Type } from '../utils/type';
import { API_BASE, API_FILTER_NAMES, API_HAS_NAME } from '../utils/api';
import { SMALL_PAGE_SIZE } from 'src/app/utils/constants';
import { UniqueCheck } from 'src/app/utils/unique-check';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TypeService {

  constructor(
    private http: HttpClient
  ) { }
    
  list(page: number): Observable<HttpResponse<Type[]>>{
    return this.http.get<Type[]>(`${API_BASE}?page=${page}&size=${SMALL_PAGE_SIZE}`, 
     { observe: 'response' }
    ).pipe(
      catchError(() => of(null))
    );
  }

  save(data: FormData): Observable<null>{
    return this.http.post<null>(API_BASE, data);
  }

  delete(id: number): Observable<null>{
    return this.http.delete<null>(`${API_BASE}/${id}`);
  }
  
  hasName(param: UniqueCheck): Observable<boolean>{
    return this.http.post<boolean>(API_HAS_NAME, param);
  }
  
  filterNames(filter: string): Observable<string[]>{
    return this.http.post<string[]>(API_FILTER_NAMES, filter);
  }

}
