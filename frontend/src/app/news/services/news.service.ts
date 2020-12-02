import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { SMALL_PAGE_SIZE } from 'src/app/utils/constants';
import { News } from '../news';
import { environment } from 'src/environments/environment';
import { NewsFilterParams } from '../news-filter-params';

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  constructor(
    private http: HttpClient
  ) { }

  private readonly API_NEWS = `${environment.baseUrl}/api/news`;
  private readonly API_OFFERS = `${environment.baseUrl}/api/cultural_offers`;

  filter(filters: NewsFilterParams, culturalOfferId: number, page: number): Observable<HttpResponse<News[]>>{
    const params = new HttpParams().set('page', page + '').set('size', SMALL_PAGE_SIZE + '');
    return this.http.post<News[]>(`${this.API_OFFERS}/${culturalOfferId}/filter_news`, filters, {observe: 'response', params}).pipe(
      catchError(() => of(null))
    );
  }

  save(culturalOfferId: number, data: FormData): Observable<null>{
    return this.http.post<null>( `${this.API_OFFERS}/${culturalOfferId}/news`, data);
  }

  delete(id: number): Observable<null>{
    return this.http.delete<null>(`${this.API_NEWS}/${id}`);
  }

}
