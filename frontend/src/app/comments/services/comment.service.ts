import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { SMALL_PAGE_SIZE } from 'src/app/utils/constants';
import { API_COMMENTS_BASE, API_COMMENTS } from '../utils/api';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(
    private http: HttpClient
  ) { }

  list(culturalOfferId: number, page: number): Observable<HttpResponse<Comment[]>>{
    return this.http.get<Comment[]>(`${API_COMMENTS_BASE}/${culturalOfferId}/comments?page=${page}&size=${SMALL_PAGE_SIZE}`, {observe: "response"}).pipe(
      catchError(() => of(null))
    );
  }

  save(culturalOfferId: number, data: FormData): Observable<null>{
    return this.http.post<null>( `${API_COMMENTS_BASE}/${culturalOfferId}/comment`, data);
  }

  delete(id: number): Observable<null>{
    return this.http.delete<null>(`${API_COMMENTS}/${id}`);
  }

}
