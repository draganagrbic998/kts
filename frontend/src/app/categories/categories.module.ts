import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LayoutModule } from '../layout/layout.module';
import { CategoriesListComponent } from './categories-list/categories-list.component';
import { CategoriesDialogComponent } from './categories-dialog/categories-dialog.component';



@NgModule({
  declarations: [CategoriesListComponent, CategoriesDialogComponent],
  imports: [
    LayoutModule,
    CommonModule
    ],
  exports: [
    CategoriesDialogComponent
  ]
})
export class CategoriesModule { }
