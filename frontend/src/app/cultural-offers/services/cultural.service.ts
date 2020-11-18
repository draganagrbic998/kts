import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { API_FILTER_NAMES } from '../utils/api';

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

}
