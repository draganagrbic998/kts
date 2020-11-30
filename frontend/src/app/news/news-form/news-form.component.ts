import { Component, EventEmitter, Inject, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ImageService } from 'src/app/services/image.service';
import { ERROR_MESSAGE, ERROR_SNACKBAR_OPTIONS, SNACKBAR_CLOSE } from 'src/app/utils/constants';
import { Image } from 'src/app/utils/image';
import { NewsService } from '../services/news.service';
import { News } from '../utils/news';

@Component({
  selector: 'app-news-form',
  templateUrl: './news-form.component.html',
  styleUrls: ['./news-form.component.sass']
})
export class NewsFormComponent implements OnInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) private news: News, 
    private newsService: NewsService, 
    private imageService: ImageService,
    public dialogRef: MatDialogRef<NewsFormComponent>,
    private snackBar: MatSnackBar
  ) { }

  text: FormControl = new FormControl(this.news.text || '', [Validators.required, Validators.pattern(new RegExp("\\S"))])
  images: Image[] = this.news.images ? this.news.images.map(img => {return {path: img, upload: null}}) : [];
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
    if (this.news.id){
      formData.append("id", this.news.id + "");
    }
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
    this.newsService.save(this.news.culturalOfferId, formData).subscribe(
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
