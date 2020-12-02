import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { LARGE_PAGE_SIZE } from 'src/app/utils/constants';
import { environment } from 'src/environments/environment';
import { CulturalOffer } from '../utils/cultural-offer';
import { FilterParams } from '../utils/filter-params';

@Injectable({
  providedIn: 'root'
})
export class UserFollowingService {

  constructor(
    private http: HttpClient
  ) { }

  private readonly API_OFFERS = `${environment.baseUrl}/api/cultural_offers`;

  filter(filters: FilterParams, page: number): Observable<HttpResponse<CulturalOffer[]>>{
    const params = new HttpParams().set('page', page + '').set('size', LARGE_PAGE_SIZE + '');
    return this.http.post<CulturalOffer[]>(`${this.API_OFFERS}/filter_followings`, filters, {observe: 'response', params}).pipe(
      catchError(() => of(null))
    );
  }

  toggleSubscription(culturalOfferId: number): Observable<CulturalOffer>{
    return this.http.post<CulturalOffer>(`${this.API_OFFERS}/${culturalOfferId}/toggle_subscription`, null);
  }

}
