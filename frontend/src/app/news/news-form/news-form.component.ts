import { Component, EventEmitter, Inject, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ERROR_MESSAGE, ERROR_SNACKBAR_OPTIONS, SNACKBAR_CLOSE } from 'src/app/constants/dialog';
import { Image } from 'src/app/models/image';
import { News } from 'src/app/models/news';
import { ImageService } from 'src/app/services/image/image.service';
import { NewsService } from 'src/app/services/news/news.service';

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

  savePending = false;
  saved: EventEmitter<null> = new EventEmitter();
  text: FormControl = new FormControl(this.news.text || '', [Validators.required, Validators.pattern(new RegExp('\\S'))]);
  images: Image[] = this.news.images ? this.news.images.map(img => {
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
    if (this.news.id){
      formData.append('id', this.news.id + '');
    }
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
    this.newsService.save(this.news.culturalOfferId, formData).subscribe(
      () => {
        this.dialogRef.close();
        this.saved.emit();
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
