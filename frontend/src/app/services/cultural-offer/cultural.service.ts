import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { LARGE_PAGE_SIZE } from 'src/app/constants/pagination';
import { CulturalOffer } from 'src/app/models/cultural-offer';
import { FilterParams } from 'src/app/models/filter-params';
import { UniqueCheck } from 'src/app/models/unique-check';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CulturalService {

  constructor(
    private http: HttpClient
  ) { }

  private readonly API_OFFERS = `${environment.baseUrl}/${environment.apiCulturalOffers}`;

  filterNames(filter: string): Observable<string[]>{
    return this.http.post<string[]>(`${this.API_OFFERS}/filter_names`, {value: filter}).pipe(
      catchError(() => of([]))
    );
  }

  filterLocations(filter: string): Observable<string[]>{
    return this.http.post<string[]>(`${this.API_OFFERS}/filter_locations`, {value: filter}).pipe(
      catchError(() => of([]))
    );
  }

  filterTypes(filter: string): Observable<string[]>{
    return this.http.post<string[]>(`${this.API_OFFERS}/filter_types`, {value: filter}).pipe(
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
    return this.http.post<{value: boolean}>(`${this.API_OFFERS}/has_name`, param).pipe(
      map((response: {value: boolean}) => response.value)
    );
  }

}
