import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DeleteConfirmationComponent } from 'src/app/layout/delete-confirmation/delete-confirmation.component';
import { AuthService } from 'src/app/services/auth.service';
import { DIALOG_OPTIONS, SNACKBAR_CLOSE, SUCCESS_SNACKBAR_OPTIONS } from 'src/app/utils/constants';
import { CommentFormComponent } from '../comment-form/comment-form.component';
import { CommentService } from '../services/comment.service';
import { Comment } from '../utils/comment';

@Component({
  selector: 'app-comment-details',
  templateUrl: './comment-details.component.html',
  styleUrls: ['./comment-details.component.sass']
})
export class CommentDetailsComponent implements OnInit {

  constructor(
    private authService: AuthService,
    private commentService: CommentService,
    private dialog: MatDialog, 
    private snackBar: MatSnackBar
  ) { }

  @Input() culturalOfferId: number;
  @Input() comment: Comment;
  @Output() onRefreshData: EventEmitter<null> = new EventEmitter();
  panelOpenState: boolean = false;

  get email(): string{
    return this.authService.getUser()?.email;
  }

  edit(): void{

    const comment = {...this.comment, ...{culturalOfferId: this.culturalOfferId}};
    const options = {...DIALOG_OPTIONS, ...{data: comment}};
    const dialog: MatDialogRef<CommentFormComponent> = this.dialog.open(CommentFormComponent, options);
      dialog.componentInstance.onSaved.subscribe(
        () => {
          this.onRefreshData.emit();
        }
    );
  }

  delete(): void{
    const options = {...DIALOG_OPTIONS, ...{data: () => this.commentService.delete(this.comment.id)}}
    const dialog: MatDialogRef<DeleteConfirmationComponent> = this.dialog.open(DeleteConfirmationComponent, options);
    dialog.componentInstance.onDeleted.subscribe(
      () => {
        this.onRefreshData.emit();
        this.snackBar.open("Comment successfully deleted!", SNACKBAR_CLOSE, SUCCESS_SNACKBAR_OPTIONS);
      }
    );
  }

  ngOnInit(): void {
  }

}
