import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { FIRST_PAGE_HEADER, LAST_PAGE_HEADER } from 'src/app/utils/constants';
import { NewsService } from '../services/news.service';
import { News } from '../utils/news';

@Component({
  selector: 'app-news-list',
  templateUrl: './news-list.component.html',
  styleUrls: ['./news-list.component.sass']
})
export class NewsListComponent implements OnInit {

  constructor(
    private newsService: NewsService 
  ) { }

  @Input() culturalOfferId: number;
  news: News[];
  fetchPending: boolean = true;
  pageNumber: number = 0;
  startOfPages: boolean = true;
  endOfPages: boolean = true;

  changePage(value: number): void{
    this.pageNumber += value;
    this.fetchNews();
  }

  fetchNews(): void{
    this.fetchPending = true;
    this.newsService.list(this.culturalOfferId, this.pageNumber).subscribe(
      (data: HttpResponse<News[]>) => {
        this.fetchPending = false;
        if (data){
          this.news = data.body;
          const headers: HttpHeaders = data.headers;
          this.endOfPages = headers.get(LAST_PAGE_HEADER) === "true" ? true : false;
          this.startOfPages = headers.get(FIRST_PAGE_HEADER) === "true" ? true : false;
        }
        else{
          this.endOfPages = true;
          this.startOfPages = true;
        }
      }
    );
  }

  ngOnInit(): void {
    this.fetchNews();
  }

}
