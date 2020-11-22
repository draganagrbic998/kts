import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmationDialogComponent } from 'src/app/layout/confirmation-dialog/confirmation-dialog.component';
import { ERROR_SNACKBAR_OPTIONS, SUCCESS_SNACKBAR_OPTIONS } from 'src/app/utils/constants';
import { AuthService } from 'src/app/utils/services/auth.service';
import { NewsService } from '../services/news.service';
import { News } from '../utils/news';

@Component({
  selector: 'app-news-details',
  templateUrl: './news-details.component.html',
  styleUrls: ['./news-details.component.sass']
})
export class NewsDetailsComponent implements OnInit {

  constructor(
    private authService: AuthService,
    private newsService: NewsService,
    private snackBar: MatSnackBar,
    public dialog: MatDialog
  ) { }

  @Input() currentNews: News;
  @Output() onRefreshData: EventEmitter<News | number> = new EventEmitter();

  delPending: boolean = false;

  hasPreviousImage: boolean = false;;
  hasNextImage: boolean;
  currentImageIndex: number = 0;

  get role(): string{
    return this.authService.getUser()?.role;
  }

  previousImage(): void {
    this.currentImageIndex--;
    this.hasNextImage = true;

    if (this.currentImageIndex === 0)
      this.hasPreviousImage = false;
  }

  nextImage(): void {
    this.currentImageIndex++;
    this.hasPreviousImage = true;

    if (this.currentImageIndex === this.currentNews.images.length - 1)
      this.hasNextImage = false;
  }

  edit(): void {
  }

  delete(): void {
    const dialog: MatDialogRef<ConfirmationDialogComponent> = this.dialog.open(ConfirmationDialogComponent, 
      {disableClose: true, panelClass: "no-padding", data: "Delete news?"});

      dialog.afterClosed().subscribe((result) => {
        if (result) {
          this.delPending = true;
    
          this.newsService.delete(this.currentNews.id).subscribe(
            () => {
              this.delPending = false;

              this.onRefreshData.emit(this.currentNews.id);
        
              this.snackBar.open("You have successfully deleted this news!", "Close", SUCCESS_SNACKBAR_OPTIONS);
            }, 
            () => {
              this.delPending = false;
              this.snackBar.open("An error occured! Try again.", 
              "Close", ERROR_SNACKBAR_OPTIONS);
            }
          )
        }
      });
  }
  
  ngOnInit(): void {
    if (this.currentNews.images.length > 0)
      this.hasNextImage = true;
  }

}
