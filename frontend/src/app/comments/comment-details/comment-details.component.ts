import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Comment } from 'src/app/models/comment';
import { CommentService } from 'src/app/comments/services/comment.service';
import { CommentFormComponent } from '../comment-form/comment-form.component';
import { AuthService } from 'src/app/shared/services/auth/auth.service';
import { DIALOG_OPTIONS } from 'src/app/constants/dialog';
import { DeleteConfirmationComponent } from 'src/app/shared/controls/delete-confirmation/delete-confirmation.component';

@Component({
  selector: 'app-comment-details',
  templateUrl: './comment-details.component.html',
  styleUrls: ['./comment-details.component.sass']
})
export class CommentDetailsComponent implements OnInit {

  constructor(
    private authService: AuthService,
    private commentService: CommentService,
    private dialog: MatDialog
  ) { }

  @Input() comment: Comment;

  get email(): string{
    return this.authService.getUser()?.email;
  }

  edit(): void{
    const options = {...DIALOG_OPTIONS, ...{data: this.comment}};
    this.dialog.open(CommentFormComponent, options);
  }

  delete(): void{
    const options = {...DIALOG_OPTIONS, ...{data: () => this.commentService.delete(this.comment.id)}};
    this.dialog.open(DeleteConfirmationComponent, options).afterClosed().subscribe(result => {
      if (typeof result === 'number'){
        this.commentService.announceRefreshData({id: this.comment.culturalOfferId, totalRate: result});
      }
    });
  }

  ngOnInit(): void {
  }

}
