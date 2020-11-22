import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { API_GET } from '../utils/api';
import { SMALL_PAGE_SIZE } from 'src/app/utils/constants';
import { News } from '../utils/news';

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  constructor(
    private http: HttpClient
  ) { }

  fetch(culturalOfferId: number, pageNumber: number) : Observable<HttpResponse<News[]>>{
    return this.http.post<News[]>(`${API_GET}?page=${pageNumber}&size=${SMALL_PAGE_SIZE}`, culturalOfferId, { observe: 'response' }).pipe(
      catchError(() => of(null))
    );
  }
}
