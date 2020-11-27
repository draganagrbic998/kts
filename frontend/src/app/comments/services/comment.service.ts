import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { MEDIUM_PAGE_SIZE } from 'src/app/utils/constants';
import { API_LIST } from '../utils/api';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(
    private http: HttpClient
  ) { }

  list(culturalOfferId: number, page: number): Observable<HttpResponse<Comment[]>>{
    return this.http.get<Comment[]>(`${API_LIST}/${culturalOfferId}/comments?page=${page}&size=${MEDIUM_PAGE_SIZE}`, {observe: "response"}).pipe(
      catchError(() => of(null))
    );
  }

}
