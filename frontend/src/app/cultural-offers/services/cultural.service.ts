import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { LARGE_PAGE_SIZE } from 'src/app/utils/constants';
import { API_FILTER_LOCATIONS, API_FILTER_NAMES, API_FILTER_TYPES, API_FILTER, API_BASE } from '../utils/api';
import { CulturalOffer } from '../utils/cultural-offer';
import { FilterParams } from '../utils/filter-params';

@Injectable({
  providedIn: 'root'
})
export class CulturalService {

  constructor(
    private http: HttpClient
  ) { }

  filterNames(filter: string): Observable<string[]>{
    return this.http.post<string[]>(API_FILTER_NAMES, filter).pipe(
      catchError(() => of([]))
    );
  }

  filterLocations(filter: string): Observable<string[]>{
    return this.http.post<string[]>(API_FILTER_LOCATIONS, filter).pipe(
      catchError(() => of([]))
    );
  }

  filterTypes(filter: string): Observable<string[]>{
    return this.http.post<string[]>(API_FILTER_TYPES, filter).pipe(
      catchError(() => of([]))
    );
  }

  filter(filters: FilterParams, page: number): Observable<HttpResponse<CulturalOffer[]>>{
    return this.http.post<CulturalOffer[]>(`${API_FILTER}?page=${page}&size=${LARGE_PAGE_SIZE}`, 
    filters, { observe: 'response' }).pipe(
      catchError(() => of(null))
    );
  }

  delete(id: number): Observable<CulturalOffer>{
    return this.http.delete<CulturalOffer>(`${API_BASE}/${id}`);
  }
}
