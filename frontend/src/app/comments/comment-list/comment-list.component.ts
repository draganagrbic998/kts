import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FIRST_PAGE_HEADER, LAST_PAGE_HEADER } from 'src/app/constants/pagination';
import { CommentService } from 'src/app/services/comment/comment.service';

@Component({
  selector: 'app-comment-list',
  templateUrl: './comment-list.component.html',
  styleUrls: ['./comment-list.component.sass']
})
export class CommentListComponent implements OnInit {

  constructor(
    private commentService: CommentService
  ) { }

  @Input() culturalOfferId: number;
  comments: Comment[];
  fetchPending = true;
  pageNumber = 0;
  endOfPages = true;
  startOfPages = true;
  @Output() updateTotalRate: EventEmitter<number> = new EventEmitter();

  changePage(value: number): void{
    this.pageNumber += value;
    this.fetchComments();
  }

  fetchComments(): void{
    this.fetchPending = true;
    this.commentService.list(this.culturalOfferId, this.pageNumber).subscribe(
      (data: HttpResponse<Comment[]>) => {
        this.fetchPending = false;
        if (data){
          this.comments = data.body;
          const headers: HttpHeaders = data.headers;
          this.endOfPages = headers.get(LAST_PAGE_HEADER) === 'true' ? true : false;
          this.startOfPages = headers.get(FIRST_PAGE_HEADER) === 'true' ? true : false;
        }
        else{
          this.comments = [];
          this.endOfPages = true;
          this.startOfPages = true;
        }
      }
    );
  }

  ngOnInit(): void {
    this.fetchComments();
  }

}
