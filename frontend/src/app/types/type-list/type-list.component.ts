import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FIRST_PAGE_HEADER, LAST_PAGE_HEADER } from 'src/app/utils/constants';
import { TypeService } from '../services/type.service';
import { Type } from '../type';

@Component({
  selector: 'app-type-list',
  templateUrl: './type-list.component.html',
  styleUrls: ['./type-list.component.sass']
})
export class TypeListComponent implements OnInit {

  constructor(
    private typeService: TypeService
  ) { }

  types: Type[];
  fetchPending = true;
  pageNumber = 0;
  endOfPages = true;
  startOfPages = true;

  changePage(value: number): void{
    this.pageNumber += value;
    this.fetchData();
  }

  fetchData(): void{
    this.fetchPending = true;
    this.typeService.list(this.pageNumber).subscribe(
      (data: HttpResponse<Type[]>) => {
        this.fetchPending = false;
        if (data){
          this.types = data.body;
          const headers: HttpHeaders = data.headers;
          this.endOfPages = headers.get(LAST_PAGE_HEADER) === 'true' ? true : false;
          this.startOfPages = headers.get(FIRST_PAGE_HEADER) === 'true' ? true : false;
        }
        else{
          this.types = [];
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
