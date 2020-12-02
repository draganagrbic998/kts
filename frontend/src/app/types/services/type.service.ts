import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Type } from '../type';
import { SMALL_PAGE_SIZE } from 'src/app/utils/constants';
import { UniqueCheck } from 'src/app/utils/unique-check';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TypeService {

  constructor(
    private http: HttpClient
  ) { }

  private readonly API_TYPES = `${environment.baseUrl}/api/types`;

  list(page: number): Observable<HttpResponse<Type[]>>{
    const params = new HttpParams().set('page', page + '').set('size', SMALL_PAGE_SIZE + '');
    return this.http.get<Type[]>(`${this.API_TYPES}`, { observe: 'response', params }).pipe(
      catchError(() => of(null))
    );
  }

  save(data: FormData): Observable<null>{
    return this.http.post<null>(this.API_TYPES, data);
  }

  delete(id: number): Observable<null>{
    return this.http.delete<null>(`${this.API_TYPES}/${id}`);
  }

  hasName(param: UniqueCheck): Observable<boolean>{
    return this.http.post<boolean>(`${this.API_TYPES}/has_name`, param);
  }

  filterNames(filter: string): Observable<string[]>{
    return this.http.post<string[]>(`${this.API_TYPES}/filter_names`, filter);
  }

}
