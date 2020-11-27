import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_BASE } from '../utils/api';
import { Category } from '../utils/category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(
    private http: HttpClient
  ) { }

  list(): Observable<Category[]>{
    return this.http.get<Category[]>(API_BASE);
  }

  save(category: Category): Observable<null>{
    return this.http.post<null>(API_BASE, category);
  }

  delete(id: number): Observable<null>{
    return this.http.delete<null>(`${API_BASE}${id}`);
  }

}
