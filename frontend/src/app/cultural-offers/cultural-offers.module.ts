import { NgModule } from '@angular/core';
import { CulturalDetailsComponent } from './cultural-details/cultural-details.component';
import { LayoutModule } from '../layout/layout.module';
import { CulturalListComponent } from './cultural-list/cultural-list.component';



@NgModule({
  declarations: [CulturalDetailsComponent, CulturalListComponent],
  imports: [
    LayoutModule
  ]
})
export class CulturalOffersModule { }
