import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UniqueCheck } from 'src/app/utils/unique-check';
import { Category } from '../utils/category';
import { API_LIST } from '../utils/api';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(
    private http: HttpClient
  ) { }


  getAllCategories(): Observable<Category[]>{
    return this.http.get<Category[]>(API_LIST);
  }

  

}
