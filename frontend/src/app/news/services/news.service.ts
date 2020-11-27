import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { API_BASE, API_LIST } from '../utils/api';
import { MEDIUM_PAGE_SIZE } from 'src/app/utils/constants';
import { News } from '../utils/news';

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  constructor(
    private http: HttpClient
  ) { }

  list(culturalOfferId: number, page: number): Observable<HttpResponse<News[]>>{
    return this.http.get<News[]>(`${API_LIST}/${culturalOfferId}/news?page=${page}&size=${MEDIUM_PAGE_SIZE}`, 
    {observe: "response"}).pipe(
      catchError(() => of(null))
    );
  }

  delete(id: number): Observable<null>{
    return this.http.delete<null>(`${API_BASE}/${id}`);
  }
}
