import { NgModule } from '@angular/core';
import { CulturalDetailsComponent } from './cultural-details/cultural-details.component';
import { LayoutModule } from '../layout/layout.module';
import { CulturalListComponent } from './cultural-list/cultural-list.component';
import { CulturalDialogComponent } from './cultural-dialog/cultural-dialog.component';
import { NewsModule } from '../news/news.module';
import { CommentsModule } from '../comments/comments.module';
import { CulturalFormComponent } from './cultural-form/cultural-form.component';

@NgModule({
  declarations: [
    CulturalDetailsComponent,
    CulturalListComponent,
    CulturalDialogComponent,
    CulturalFormComponent
  ],
  imports: [
    LayoutModule,
    CommentsModule,
    NewsModule
  ],
  exports: [
    CulturalListComponent,
    CulturalDialogComponent,
    CulturalFormComponent
  ]
})
export class CulturalOffersModule { }
