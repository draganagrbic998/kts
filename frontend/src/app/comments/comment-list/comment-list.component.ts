import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { FIRST_PAGE_HEADER, LAST_PAGE_HEADER } from 'src/app/utils/constants';
import { CommentService } from '../services/comment.service';

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
  fetchPending: boolean = true;
  pageNumber: number = 0;
  endOfPages: boolean = true;
  startOfPages: boolean = true;

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
    this.fetchComments();
  }

}