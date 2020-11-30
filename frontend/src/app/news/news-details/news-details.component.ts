import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DeleteConfirmationComponent } from 'src/app/layout/delete-confirmation/delete-confirmation.component';
import { AuthService } from 'src/app/services/auth.service';
import { ADMIN_ROLE, DIALOG_OPTIONS, SNACKBAR_CLOSE, SUCCESS_SNACKBAR_OPTIONS } from 'src/app/utils/constants';
import { NewsFormComponent } from '../news-form/news-form.component';
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
    private dialog: MatDialog,
    private snackBar: MatSnackBar
  ) { }

  @Input() culturalOfferId: number;
  @Input() news: News;
  @Output() onRefreshData: EventEmitter<null> = new EventEmitter();

  get admin(): boolean{
    return this.authService.getUser()?.role === ADMIN_ROLE;
  }

  edit(): void {
    const news = {...this.news, ...{culturalOfferId: this.culturalOfferId}};
    const options = {...DIALOG_OPTIONS, ...{data: news}};
    const dialog: MatDialogRef<NewsFormComponent> = this.dialog.open(NewsFormComponent, options);
      dialog.componentInstance.onSaved.subscribe(
        () => {
          this.onRefreshData.emit();
        }
    );
  }

  delete(): void {
    const options = {...DIALOG_OPTIONS, ...{data: () => this.newsService.delete(this.news.id)}}
    const dialog: MatDialogRef<DeleteConfirmationComponent> = this.dialog.open(DeleteConfirmationComponent, options);
    dialog.componentInstance.onDeleted.subscribe(
      () => {
        this.onRefreshData.emit();
      }
    );
  }
  
  ngOnInit(): void {
  }

}
