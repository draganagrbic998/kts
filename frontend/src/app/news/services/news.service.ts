import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of, Subject } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { SMALL_PAGE_SIZE } from 'src/app/constants/pagination';
import { Image } from 'src/app/models/image';
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

  filter(filters: NewsFilterParams, culturalOfferId: number, page: number): Observable<HttpResponse<News[]>>{
    const params = new HttpParams().set('page', page + '').set('size', SMALL_PAGE_SIZE + '');
    return this.http.post<News[]>(`${this.API_OFFERS}/${culturalOfferId}/filter_news`, filters, {observe: 'response', params}).pipe(
      catchError(() => of(null))
    );
  }

  save(news: News, images: Image[]): Observable<null>{
    // dodaj da ti se sa backenda vraca news i onda u catchError dodaj
    // da se vrati null kao indikator neuspeha (pogledaj culturalservice)
    //  da vidis na koji je fazon, tako ces lepse testirati
    return this.http.post<null>(this.API_NEWS, this.newsToFormData(news, images));
  }

  delete(id: number): Observable<boolean>{
    return this.http.delete<null>(`${this.API_NEWS}/${id}`).pipe(
      map(() => true),
      catchError(() => of(false))
    );
  }

  announceRefreshData(culturalOfferId: number): void{
    this.refreshData.next(culturalOfferId);
  }

  newsToFormData(news: News, images: Image[]): FormData{
    const formData: FormData = new FormData();
    if (news.id){
      formData.append('id', news.id + '');
    }
    formData.append('culturalOfferId', news.culturalOfferId + '');
    formData.append('text', news.text);

    for (const image of images){
      if (image.upload){
        formData.append('images', image.upload);
      }
      else if (image.path){
        formData.append('imagePaths', image.path);
      }
    }
    return formData;

  }

}
