import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FIRST_PAGE_HEADER, LAST_PAGE_HEADER } from 'src/app/utils/constants';
import { CategoryService } from '../services/category.service';
import { Category } from '../utils/category';

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
  fetchPending: boolean = true;
  pageNumber : number = 0;
  startOfPages: boolean = true;
  endOfPages : boolean = true;


  ngOnInit(): void {
    this.fetchData();
    
  }


  fetchData(): void{
    this.fetchPending = true;
    this.categories = [];
    this.categoryService.list(this.pageNumber).subscribe(
      (data: HttpResponse<Category[]>) => {
        this.fetchPending = false;
        if (data){
          this.categories = data.body;
          const headers: HttpHeaders = data.headers;
          this.endOfPages = headers.get(LAST_PAGE_HEADER) === "true" ? true : false;
          this.startOfPages = headers.get(FIRST_PAGE_HEADER) === "true" ? true : false;
        }
        else{
          console.log("nema");
          this.endOfPages = true;
          this.startOfPages = true;
        }
      }
    );
  }
  changePage(amount: number): void{
    this.pageNumber += amount;
    this.fetchData();
  }

}
