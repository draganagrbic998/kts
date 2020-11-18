import { NgModule } from '@angular/core';
import { CulturalDetailsComponent } from './cultural-details/cultural-details.component';
import { LayoutModule } from '../layout/layout.module';



@NgModule({
  declarations: [CulturalDetailsComponent],
  imports: [
    LayoutModule
  ]
})
export class CulturalOffersModule { }
