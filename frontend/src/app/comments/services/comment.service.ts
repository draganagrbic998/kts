import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of, Subject } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { SMALL_PAGE_SIZE } from 'src/app/constants/pagination';
import { CulturalService } from 'src/app/cultural-offers/services/cultural.service';
import { RateUpdate } from 'src/app/models/rate-update';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(
    private http: HttpClient,
    private culturalService: CulturalService
  ) { }

  private readonly API_COMMENTS = `${environment.baseUrl}/${environment.apiComments}`;
  private readonly API_OFFERS = `${environment.baseUrl}/${environment.apiCulturalOffers}`;

  private refreshData: Subject<number> = new Subject();
  refreshData$ = this.refreshData.asObservable();
  announceRefreshData(rateUpdate: RateUpdate): void{
    this.refreshData.next(rateUpdate.id);
    this.culturalService.announceUpdateTotalRate(rateUpdate);
  }

  list(culturalOfferId: number, page: number): Observable<HttpResponse<Comment[]>>{
    const params = new HttpParams().set('page', page + '').set('size', SMALL_PAGE_SIZE + '');
    return this.http.get<Comment[]>(`${this.API_OFFERS}/${culturalOfferId}/comments`, {observe: 'response', params}).pipe(
      catchError(() => of(null))
    );
  }

  save(data: FormData): Observable<number>{
    return this.http.post<{value: number}>(this.API_COMMENTS, data).pipe(
      map((response: {value: number}) => response.value)
    );
  }

  delete(id: number): Observable<number>{
    return this.http.delete<{value: number}>(`${this.API_COMMENTS}/${id}`).pipe(
      map((response: {value: number}) => response.value)
    );
  }

}
