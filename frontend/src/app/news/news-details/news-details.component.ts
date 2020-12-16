import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { DIALOG_OPTIONS } from 'src/app/constants/dialog';
import { ADMIN_ROLE } from 'src/app/constants/roles';
import { DeleteConfirmationComponent } from 'src/app/layout/delete-confirmation/delete-confirmation.component';
import { News } from 'src/app/models/news';
import { AuthService } from 'src/app/services/auth/auth.service';
import { NewsService } from 'src/app/services/news/news.service';
import { NewsFormComponent } from '../news-form/news-form.component';

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
  @Output() refreshData: EventEmitter<null> = new EventEmitter();

  get admin(): boolean{
    return this.authService.getUser()?.role === ADMIN_ROLE;
  }

  edit(): void {
    const options = {...DIALOG_OPTIONS, ...{data: this.news}};
    const dialog: MatDialogRef<NewsFormComponent> = this.dialog.open(NewsFormComponent, options);
    dialog.componentInstance.saved.subscribe(
      () => {
        this.refreshData.emit();
      }
    );
  }

  delete(): void {
    const options = {...DIALOG_OPTIONS, ...{data: () => this.newsService.delete(this.news.id)}};
    const dialog: MatDialogRef<DeleteConfirmationComponent> = this.dialog.open(DeleteConfirmationComponent, options);
    dialog.componentInstance.deleted.subscribe(
      () => {
        this.refreshData.emit();
      }
    );
  }

  ngOnInit(): void {
  }

}
