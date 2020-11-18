import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormContainerComponent } from './form-container/form-container.component';

import { MatCardModule } from '@angular/material/card'

@NgModule({
  declarations: [FormContainerComponent],
  imports: [
    CommonModule,
    MatCardModule
  ]
})
export class LayoutModule { }
