import { NgModule } from '@angular/core';
import { LayoutModule } from '../layout/layout.module';
import { NewsListComponent } from './news-list/news-list.component';
import { NewsDetailsComponent } from './news-details/news-details.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';

@NgModule({
  declarations: [
    NewsDetailsComponent,
    NewsListComponent, 
  ],
  imports: [
    MatDatepickerModule,
    MatNativeDateModule,
    LayoutModule,
  ],
  exports: [
    NewsListComponent
  ]
})
export class NewsModule { }
