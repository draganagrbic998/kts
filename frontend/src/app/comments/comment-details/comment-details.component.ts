import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { DIALOG_OPTIONS } from 'src/app/constants/dialog';
import { DeleteConfirmationComponent } from 'src/app/layout/delete-confirmation/delete-confirmation.component';
import { Comment } from 'src/app/models/comment';
import { AuthService } from 'src/app/services/auth/auth.service';
import { CommentService } from 'src/app/services/comment/comment.service';
import { CommentFormComponent } from '../comment-form/comment-form.component';

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
  @Output() refreshData: EventEmitter<number> = new EventEmitter();

  get email(): string{
    return this.authService.getUser()?.email;
  }

  edit(): void{

    const options = {...DIALOG_OPTIONS, ...{data: this.comment}};
    const dialog: MatDialogRef<CommentFormComponent> = this.dialog.open(CommentFormComponent, options);
    dialog.componentInstance.saved.subscribe(
      (totalRate: number) => {
        this.refreshData.emit(totalRate);
      }
    );
  }

  delete(): void{
    const options = {...DIALOG_OPTIONS, ...{data: () => this.commentService.delete(this.comment.id)}};
    const dialog: MatDialogRef<DeleteConfirmationComponent> = this.dialog.open(DeleteConfirmationComponent, options);
    dialog.componentInstance.deleted.subscribe(
      (totalRate: number) => {
        this.refreshData.emit(totalRate);
      }
    );
  }

  ngOnInit(): void {
  }

}
