import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { SMALL_PAGE_SIZE } from 'src/app/utils/constants';
import { UniqueCheck } from 'src/app/utils/unique-check';
import { API_BASE, API_HAS_NAME } from '../utils/api';
import { Category } from '../utils/category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(
    private http: HttpClient
  ) { }

  list(page: number): Observable<HttpResponse<Category[]>>{
    return this.http.get<Category[]>(`${API_BASE}?page=${page}&size=${SMALL_PAGE_SIZE}`, 
     { observe: 'response' }
    ).pipe(
      catchError(() => of(null))
    )
  }

  save(category: Category): Observable<null>{
    return this.http.post<null>(API_BASE, category);
  }

  delete(id: number): Observable<null>{
    return this.http.delete<null>(`${API_BASE}/${id}`);
  }

  hasName(param: UniqueCheck): Observable<boolean>{
    return this.http.post<boolean>(API_HAS_NAME, param);
  }

  all(){
    return this.http.get<Category[]>(`${API_BASE}/all`);
  }

}
