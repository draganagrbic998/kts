import { Component, EventEmitter, Inject, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ERROR_MESSAGE, ERROR_SNACKBAR_OPTIONS, SNACKBAR_CLOSE } from 'src/app/constants/dialog';
import { Image } from 'src/app/models/image';
import { News } from 'src/app/models/news';
import { NewsService } from 'src/app/services/news/news.service';

@Component({
  selector: 'app-news-form',
  templateUrl: './news-form.component.html',
  styleUrls: ['./news-form.component.sass']
})
export class NewsFormComponent implements OnInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) public news: News,
    private newsService: NewsService,
    public dialogRef: MatDialogRef<NewsFormComponent>,
    private snackBar: MatSnackBar
  ) { }

  savePending = false;
  saved: EventEmitter<null> = new EventEmitter();
  text: FormControl = new FormControl(this.news.text || '', [Validators.required, Validators.pattern(new RegExp('\\S'))]);
  images: Image[] = this.news.images ? this.news.images.map(img => {
    return {path: img, upload: null};
  }) : [];

  save(): void{
    const formData: FormData = new FormData();
    if (this.news.id){
      formData.append('id', this.news.id + '');
    }
    formData.append('culturalOfferId', this.news.culturalOfferId + '');
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
    this.newsService.save(formData).subscribe(
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
