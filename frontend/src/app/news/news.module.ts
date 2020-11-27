import { NgModule } from '@angular/core';
import { LayoutModule } from '../layout/layout.module';
import { NewsListComponent } from './news-list/news-list.component';
import { NewsDetailsComponent } from './news-details/news-details.component';

@NgModule({
  declarations: [
    NewsDetailsComponent,
    NewsListComponent, 
  ],
  imports: [
    LayoutModule
  ],
  exports: [
    NewsListComponent
  ]
})
export class NewsModule { }
