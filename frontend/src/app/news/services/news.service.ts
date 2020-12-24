import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of, Subject } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { SMALL_PAGE_SIZE } from 'src/app/constants/pagination';
import { News } from 'src/app/models/news';
import { NewsFilterParams } from 'src/app/models/news-filter-params';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  constructor(
    private http: HttpClient
  ) { }

  private readonly API_NEWS = `${environment.baseUrl}/${environment.apiNews}`;
  private readonly API_OFFERS = `${environment.baseUrl}/${environment.apiCulturalOffers}`;

  private refreshData: Subject<number> = new Subject();
  refreshData$ = this.refreshData.asObservable();
  announceRefreshData(culturalOfferId: number): void{
    this.refreshData.next(culturalOfferId);
  }

  filter(filters: NewsFilterParams, culturalOfferId: number, page: number): Observable<HttpResponse<News[]>>{
    const params = new HttpParams().set('page', page + '').set('size', SMALL_PAGE_SIZE + '');
    return this.http.post<News[]>(`${this.API_OFFERS}/${culturalOfferId}/filter_news`, filters, {observe: 'response', params}).pipe(
      catchError(() => of(null))
    );
  }

  save(data: FormData): Observable<null>{
    return this.http.post<null>(this.API_NEWS, data);
  }

  delete(id: number): Observable<null>{
    return this.http.delete<null>(`${this.API_NEWS}/${id}`);
  }

}
