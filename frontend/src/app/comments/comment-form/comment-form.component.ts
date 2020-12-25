import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SNACKBAR_ERROR_MESSAGE, SNACKBAR_ERROR_OPTIONS, SNACKBAR_CLOSE } from 'src/app/constants/snackbar';
import { Comment } from 'src/app/models/comment';
import { Image } from 'src/app/models/image';
import { CommentService } from 'src/app/comments/services/comment.service';
import { CulturalService } from 'src/app/cultural-offers/services/cultural.service';

@Component({
  selector: 'app-comment-form',
  templateUrl: './comment-form.component.html',
  styleUrls: ['./comment-form.component.sass']
})
export class CommentFormComponent implements OnInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) public comment: Comment,
    private commentService: CommentService,
    private culturalService: CulturalService,
    public dialogRef: MatDialogRef<CommentFormComponent>,
    private snackBar: MatSnackBar
  ) { }

  savePending = false;
  rate: number = this.comment.rate || 0;
  text: FormControl = new FormControl(this.comment.text || '',
  [Validators.required, Validators.pattern(new RegExp('\\S'))]);
  images: Image[] = this.comment.images.map(img => {
    return {path: img, upload: null};
  });

  save(): void{
    if (this.text.invalid){
      return;
    }
    const formData: FormData = new FormData();
    if (this.comment.id){
      formData.append('id', this.comment.id + '');
    }
    formData.append('culturalOfferId', this.comment.culturalOfferId + '');
    formData.append('rate', this.rate + '');
    formData.append('text', this.text.value);
    for (const image of this.images){
      if (image.upload){
        formData.append('images', image.upload);
      }
      else{
        formData.append('imagePaths', image.path);
      }
    }

    this.savePending = true;
    this.commentService.save(formData).subscribe(
      (result: number) => {
        this.dialogRef.close();
        this.commentService.announceRefreshData({id: this.comment.culturalOfferId, totalRate: result});
      },
      () => {
        this.savePending = false;
        this.snackBar.open(SNACKBAR_ERROR_MESSAGE, SNACKBAR_CLOSE, SNACKBAR_ERROR_OPTIONS);
      }
    );

  }

  ngOnInit(): void {
  }

}
