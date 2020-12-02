import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { LARGE_PAGE_SIZE } from 'src/app/utils/constants';
import { UniqueCheck } from 'src/app/utils/unique-check';
import { environment } from 'src/environments/environment';
import { CulturalOffer } from '../utils/cultural-offer';
import { FilterParams } from '../utils/filter-params';

@Injectable({
  providedIn: 'root'
})
export class CulturalService {

  constructor(
    private http: HttpClient
  ) { }

  private readonly API_OFFERS = `${environment.baseUrl}/api/cultural_offers`;

  filterNames(filter: string): Observable<string[]>{
    return this.http.post<string[]>(`${this.API_OFFERS}/filter_names`, filter).pipe(
      catchError(() => of([]))
    );
  }

  filterLocations(filter: string): Observable<string[]>{
    return this.http.post<string[]>(`${this.API_OFFERS}/filter_locations`, filter).pipe(
      catchError(() => of([]))
    );
  }

  filterTypes(filter: string): Observable<string[]>{
    return this.http.post<string[]>(`${this.API_OFFERS}/filter_types`, filter).pipe(
      catchError(() => of([]))
    );
  }

  filter(filters: FilterParams, page: number): Observable<HttpResponse<CulturalOffer[]>>{
    const params = new HttpParams().set('page', page + '').set('size', LARGE_PAGE_SIZE + '');
    return this.http.post<CulturalOffer[]>(`${this.API_OFFERS}/filter`, filters, { observe: 'response', params }).pipe(
      catchError(() => of(null))
    );
  }

  save(data: FormData): Observable<CulturalOffer>{
    return this.http.post<CulturalOffer>(this.API_OFFERS, data);
  }

  delete(id: number): Observable<CulturalOffer>{
    return this.http.delete<CulturalOffer>(`${this.API_OFFERS}/${id}`);
  }

  hasName(param: UniqueCheck): Observable<boolean>{
    return this.http.post<boolean>(`${this.API_OFFERS}/has_name`, param);
  }

}
