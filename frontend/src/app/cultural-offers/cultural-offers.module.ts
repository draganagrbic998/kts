import { NgModule } from '@angular/core';
import { CulturalDetailsComponent } from './cultural-details/cultural-details.component';
import { LayoutModule } from '../layout/layout.module';
import { CulturalListComponent } from './cultural-list/cultural-list.component';
import { CulturalDialogComponent } from './cultural-dialog/cultural-dialog.component';
import { NewsModule } from '../news/news.module';


@NgModule({
  declarations: [CulturalDetailsComponent, CulturalListComponent, CulturalDialogComponent],
  imports: [
    LayoutModule,
    NewsModule
  ], 
  exports: [
    CulturalListComponent,
    CulturalDialogComponent
  ]
})
export class CulturalOffersModule { }