import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { API_BASE, API_FILTER } from '../utils/api';
import { MEDIUM_PAGE_SIZE } from 'src/app/utils/constants';
import { News } from '../utils/news';
import { FilterParamsNews } from '../utils/filter-params';

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  constructor(
    private http: HttpClient
  ) { }

  filter(filters: FilterParamsNews, culturalOfferId: number, page: number): Observable<HttpResponse<News[]>>{
    return this.http.post<News[]>(`${API_FILTER}/${culturalOfferId}/news/filter?page=${page}&size=${MEDIUM_PAGE_SIZE}`, 
    filters, {observe: "response"}).pipe(
      catchError(() => of(null))
    );
  }

  delete(id: number): Observable<null>{
    return this.http.delete<null>(`${API_BASE}/${id}`);
  }
}
