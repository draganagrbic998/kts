import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FIRST_PAGE_HEADER, LAST_PAGE_HEADER } from 'src/app/utils/constants';
import { CategoryService } from '../services/category.service';
import { Category } from '../category';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.sass']
})
export class CategoryListComponent implements OnInit {

  constructor(
    private categoryService: CategoryService
  ) { }

  categories: Category[];
  fetchPending = true;
  pageNumber = 0;
  startOfPages = true;
  endOfPages = true;

  changePage(value: number): void{
    this.pageNumber += value;
    this.fetchData();
  }

  fetchData(): void{
    this.fetchPending = true;
    this.categoryService.list(this.pageNumber).subscribe(
      (data: HttpResponse<Category[]>) => {
        this.fetchPending = false;
        if (data){
          this.categories = data.body;
          const headers: HttpHeaders = data.headers;
          this.endOfPages = headers.get(LAST_PAGE_HEADER) === 'true' ? true : false;
          this.startOfPages = headers.get(FIRST_PAGE_HEADER) === 'true' ? true : false;
        }
        else{
          this.categories = [];
          this.endOfPages = true;
          this.startOfPages = true;
        }
      }
    );
  }

  ngOnInit(): void {
    this.fetchData();
  }

}
