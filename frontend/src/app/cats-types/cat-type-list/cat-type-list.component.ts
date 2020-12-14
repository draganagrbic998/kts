import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { FIRST_PAGE_HEADER, LAST_PAGE_HEADER } from 'src/app/constants/pagination';
import { Category } from 'src/app/models/category';
import { Type } from 'src/app/models/type';
import { CategoryService } from 'src/app/services/category/category.service';
import { TypeService } from 'src/app/services/type/type.service';

@Component({
  selector: 'app-cat-type-list',
  templateUrl: './cat-type-list.component.html',
  styleUrls: ['./cat-type-list.component.sass']
})
export class CatTypeListComponent implements OnInit {

  constructor(
    private categoryService: CategoryService,
    private typeService: TypeService
  ) { }

  @Input() cats: boolean;
  catTypes: Category[] | Type[];
  fetchPending = true;
  pageNumber = 0;
  startOfPages = true;
  endOfPages = true;

  changePage(value: number): void{
    this.pageNumber += value;
    this.fetchData();
  }

  fetchData(): void{
    const service = this.cats ? this.categoryService : this.typeService;
    this.fetchPending = true;
    service.list(this.pageNumber).subscribe(
      (data: HttpResponse<Category[] | Type[]>) => {
        this.fetchPending = false;
        if (data){
          this.catTypes = data.body;
          const headers: HttpHeaders = data.headers;
          this.endOfPages = headers.get(LAST_PAGE_HEADER) === 'true' ? true : false;
          this.startOfPages = headers.get(FIRST_PAGE_HEADER) === 'true' ? true : false;
        }
        else{
          this.catTypes = [];
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
