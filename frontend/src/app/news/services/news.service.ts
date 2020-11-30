import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { API_NEWS, API_NEWS_BASE } from '../utils/api';
import { SMALL_PAGE_SIZE } from 'src/app/utils/constants';
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
    return this.http.post<News[]>(`${API_NEWS_BASE}/${culturalOfferId}/filter_news?page=${page}&size=${SMALL_PAGE_SIZE}`, 
    filters, {observe: "response"}).pipe(
      catchError(() => of(null))
    );
  }

  delete(id: number): Observable<null>{
    return this.http.delete<null>(`${API_NEWS}/${id}`);
  }
}
