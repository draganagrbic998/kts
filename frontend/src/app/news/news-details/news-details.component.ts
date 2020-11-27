import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { DeleteConfirmationComponent } from 'src/app/layout/delete-confirmation/delete-confirmation.component';
import { AuthService } from 'src/app/services/auth.service';
import { ADMIN_ROLE, DIALOG_OPTIONS } from 'src/app/utils/constants';
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
    private dialog: MatDialog
  ) { }

  @Input() news: News;

  get admin(): boolean{
    return this.authService.getUser()?.role === ADMIN_ROLE;
  }

  edit(): void {
  }

  delete(): void {
    const options = {...DIALOG_OPTIONS, ...{data: () => this.newsService.delete(this.news.id)}}
    const dialog: MatDialogRef<DeleteConfirmationComponent> = this.dialog.open(DeleteConfirmationComponent, options);
    dialog.componentInstance.onDeleted.subscribe(
      () => {
      }
    );
  }
  
  ngOnInit(): void {
  }

}
