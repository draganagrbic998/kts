import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { SMALL_PAGE_SIZE } from 'src/app/constants/pagination';
import { Category } from 'src/app/models/category';
import { UniqueCheck } from 'src/app/models/unique-check';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(
    private http: HttpClient
  ) { }

  private readonly API_CATEGORIES: string = `${environment.baseUrl}/${environment.apiCategories}`;

  list(page: number): Observable<HttpResponse<Category[]>>{
    const params = new HttpParams().set('page', page + '').set('size', SMALL_PAGE_SIZE + '');
    return this.http.get<Category[]>(this.API_CATEGORIES, { observe: 'response', params}).pipe(
      catchError(() => of(null))
    );
  }

  save(category: Category): Observable<null>{
    return this.http.post<null>(this.API_CATEGORIES, category);
  }

  delete(id: number): Observable<null>{
    return this.http.delete<null>(`${this.API_CATEGORIES}/${id}`);
  }

  hasName(param: UniqueCheck): Observable<boolean>{
    return this.http.post<boolean>(`${this.API_CATEGORIES}/has_name`, param);
  }

  filterNames(filter: string): Observable<string[]>{
    return this.http.post<string[]>(`${this.API_CATEGORIES}/filter_names`, filter);
  }

}
