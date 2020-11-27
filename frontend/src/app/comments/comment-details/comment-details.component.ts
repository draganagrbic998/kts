import { Component, Input, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { Comment } from '../utils/comment';

@Component({
  selector: 'app-comment-details',
  templateUrl: './comment-details.component.html',
  styleUrls: ['./comment-details.component.sass']
})
export class CommentDetailsComponent implements OnInit {

  constructor(
    private authService: AuthService
  ) { }

  @Input() comment: Comment;
  panelOpenState: boolean = false;

  get email(): string{
    return this.authService.getUser()?.email;
  }

  edit(): void{
    
  }

  delete(): void{
    
  }

  ngOnInit(): void {
  }

}
