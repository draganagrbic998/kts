import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Observable, of, Subject } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { SMALL_PAGE_SIZE } from 'src/app/constants/pagination';
import { UniqueCheck } from 'src/app/models/unique-check';
import { Type } from 'src/app/models/type';

@Injectable({
  providedIn: 'root'
})
export class TypeService {

  constructor(
    private http: HttpClient
  ) { }

  private readonly API_TYPES = `${environment.baseUrl}/${environment.apiTypes}`;

  private refreshData: Subject<void> = new Subject();
  refreshData$ = this.refreshData.asObservable();
  announceRefreshData(): void{
    this.refreshData.next();
  }

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
    return this.http.post<{value: boolean}>(`${this.API_TYPES}/has_name`, param).pipe(
      map((response: {value: boolean}) => response.value)
    );
  }

  filterNames(filter: string): Observable<string[]>{
    return this.http.post<string[]>(`${this.API_TYPES}/filter_names`, {value: filter});
  }

}