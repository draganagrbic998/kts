import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
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

  @Input() page: number;
  @Input() title: string;
  @Input() news: News[];
  @Input() fetchPending: boolean;
  @Input() hasPrevious: boolean;
  @Input() hasNext: boolean;
  @Output() onChangePage: EventEmitter<number> = new EventEmitter();

  panelOpenState: boolean = false;

  ngOnInit(): void {
  }

}
