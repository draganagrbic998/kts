import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LayoutModule } from '../layout/layout.module';
import { NewsListComponent } from './news-list/news-list.component';
import { NewsDetailsComponent } from './news-details/news-details.component';


@NgModule({
  declarations: [NewsListComponent, NewsDetailsComponent],
  imports: [
    LayoutModule,
    CommonModule
  ],
  exports: [
    NewsListComponent
  ]
})
export class NewsModule { }
