import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { LARGE_PAGE_SIZE } from 'src/app/utils/constants';
import { API_FILTER_LOCATIONS, API_FILTER_NAMES, API_FILTER_TYPES, API_FILTER, API_DELETE_OFFER } from '../utils/api';
import { CulturalOffer } from '../utils/cultural-offer';
import { FilterParams } from '../utils/filter-params';

@Injectable({
  providedIn: 'root'
})
export class CulturalService {

  constructor(
    private http: HttpClient
  ) { }

  filterNames(filterParam: string): Observable<string[]>{
    return this.http.post<string[]>(API_FILTER_NAMES, filterParam).pipe(
      catchError(() => of([]))
    );
  }

  filterLocations(filterParam: string): Observable<string[]>{
    return this.http.post<string[]>(API_FILTER_LOCATIONS, filterParam).pipe(
      catchError(() => of([]))
    );
  }

  filterTypes(filterParam: string): Observable<string[]>{
    return this.http.post<string[]>(API_FILTER_TYPES, filterParam).pipe(
      catchError(() => of([]))
    );
  }

  filter(filterParams: FilterParams, pageNumber: number): Observable<HttpResponse<CulturalOffer[]>>{
    return this.http.post<CulturalOffer[]>(`${API_FILTER}?page=${pageNumber}&size=${LARGE_PAGE_SIZE}`, filterParams, { observe: 'response' }).pipe(
      catchError(() => of(null))
    );
  }

  delete(id: number): Observable<CulturalOffer>{
    return this.http.delete<CulturalOffer>(`${API_DELETE_OFFER}/${id}`);
  }
}
