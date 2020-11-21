import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { LARGE_PAGE_SIZE } from 'src/app/utils/constants';
import { API_FILTER_FOLLOWINGS, API_TOGGLE_SUBSCRIPTION } from '../utils/api';
import { CulturalOffer } from '../utils/cultural-offer';
import { FilterParams } from '../utils/filter-params';


@Injectable({
  providedIn: 'root'
})
export class UserFollowingService {

  constructor(
    private http: HttpClient
  ) { }

  filter(filterParams: FilterParams, pageNumber: number): Observable<HttpResponse<CulturalOffer[]>>{
    return this.http.post<CulturalOffer[]>(`${API_FILTER_FOLLOWINGS}?page=${pageNumber}&size=${LARGE_PAGE_SIZE}`, filterParams, {observe: "response"}).pipe(
      catchError(() => of(null))
    );
  }

  toggleSubscription(data: number): Observable<CulturalOffer>{
    return this.http.get<CulturalOffer>(`${API_TOGGLE_SUBSCRIPTION}/${data}`);
  }

}
