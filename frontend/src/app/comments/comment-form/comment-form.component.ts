import { Component, EventEmitter, Inject, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ERROR_MESSAGE, ERROR_SNACKBAR_OPTIONS, SNACKBAR_CLOSE } from 'src/app/constants/dialog';
import { Comment } from 'src/app/models/comment';
import { Image } from 'src/app/models/image';
import { CommentService } from 'src/app/services/comment/comment.service';
import { ImageService } from 'src/app/services/image/image.service';

@Component({
  selector: 'app-comment-form',
  templateUrl: './comment-form.component.html',
  styleUrls: ['./comment-form.component.sass']
})
export class CommentFormComponent implements OnInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) public comment: Comment,
    private commentService: CommentService,
    private imageService: ImageService,
    public dialogRef: MatDialogRef<CommentFormComponent>,
    private snackBar: MatSnackBar
  ) { }

  savePending = false;
  saved: EventEmitter<number> = new EventEmitter();
  rate: number = this.comment.rate || 0;
  text: FormControl = new FormControl(this.comment.text || '', [Validators.required, Validators.pattern(new RegExp('\\S'))]);
  images: Image[] = this.comment.images ? this.comment.images.map(img => {
    return {path: img, upload: null};
  }) : [];

  addImage(upload: Blob): void{
    this.imageService.getBase64(upload)
    .then((path: string) => {
      this.images.push({path, upload});
    });
  }

  save(): void{

    const formData: FormData = new FormData();
    if (this.comment.id){
      formData.append('id', this.comment.id + '');
    }
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
    this.commentService.save(this.comment.culturalOfferId, formData).subscribe(
      (totalRate: number) => {
        this.dialogRef.close();
        this.saved.emit(totalRate);
      },
      () => {
        this.savePending = false;
        this.snackBar.open(ERROR_MESSAGE, SNACKBAR_CLOSE, ERROR_SNACKBAR_OPTIONS);
      }
    );

  }

  ngOnInit(): void {
  }

}
