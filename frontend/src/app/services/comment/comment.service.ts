import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { SMALL_PAGE_SIZE } from 'src/app/constants/pagination';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(
    private http: HttpClient
  ) { }

  private readonly API_COMMENTS = `${environment.baseUrl}/${environment.apiComments}`;
  private readonly API_OFFERS = `${environment.baseUrl}/${environment.apiCulturalOffers}`;

  list(culturalOfferId: number, page: number): Observable<HttpResponse<Comment[]>>{
    const params = new HttpParams().set('page', page + '').set('size', SMALL_PAGE_SIZE + '');
    return this.http.get<Comment[]>(`${this.API_OFFERS}/${culturalOfferId}/comments`, {observe: 'response', params}).pipe(
      catchError(() => of(null))
    );
  }

  save(culturalOfferId: number, data: FormData): Observable<number>{
    return this.http.post<number>( `${this.API_OFFERS}/${culturalOfferId}/comment`, data);
  }

  delete(id: number): Observable<number>{
    return this.http.delete<number>(`${this.API_COMMENTS}/${id}`);
  }

}