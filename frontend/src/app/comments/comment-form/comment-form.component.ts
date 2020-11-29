import { Component, EventEmitter, Inject, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ImageService } from 'src/app/services/image.service';
import { ERROR_MESSAGE, ERROR_SNACKBAR_OPTIONS, SNACKBAR_CLOSE } from 'src/app/utils/constants';
import { Image } from 'src/app/utils/image';
import { CommentService } from '../services/comment.service';
import { Comment } from '../utils/comment';

@Component({
  selector: 'app-comment-form',
  templateUrl: './comment-form.component.html',
  styleUrls: ['./comment-form.component.sass']
})
export class CommentFormComponent implements OnInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) private comment: Comment, 
    private commentService: CommentService, 
    private imageService: ImageService,
    public dialogRef: MatDialogRef<CommentFormComponent>,
    private snackBar: MatSnackBar
  ) { }

  rate: number = this.comment.rate || 0;
  text: FormControl = new FormControl(this.comment.text || '', [Validators.required, Validators.pattern(new RegExp("\\S"))])
  images: Image[] = this.comment.images ? this.comment.images.map(img => {return {path: img, upload: null}}) : [];
  savePending: boolean = false;
  onSaved: EventEmitter<null> = new EventEmitter();

  addImage(upload: Blob): void{
    this.imageService.getBase64(upload)
    .then((image: string) => {
      this.images.push({path: image, upload: upload})
    });
  }

  save(): void{

    const formData: FormData = new FormData();
    if (this.comment.id){
      formData.append("id", this.comment.id + "");
    }
    formData.append("rate", this.rate + "");
    formData.append("text", this.text.value);
    for (const img of this.images){
      if (img.upload){
        formData.append("images", img.upload);
      }
      else{
        formData.append("imagePaths", img.path);
      }
    }

    this.savePending = true;
    this.commentService.save(this.comment.culturalOfferId, formData).subscribe(
      () => {
        this.dialogRef.close();
        this.onSaved.emit();
      }, 
      () => {
        this.savePending = false;
        this.snackBar.open(ERROR_MESSAGE, SNACKBAR_CLOSE, ERROR_SNACKBAR_OPTIONS);
      }
    )

  }

  ngOnInit(): void {
  }

}
