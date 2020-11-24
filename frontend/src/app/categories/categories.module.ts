import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LayoutModule } from '../layout/layout.module';
import { CategoriesListComponent } from './categories-list/categories-list.component';
import { CategoriesDialogComponent } from './categories-dialog/categories-dialog.component';
import { AddCategoryComponent } from './add-category/add-category.component';
import { CategoryDetailsComponent } from './category-details/category-details.component';



@NgModule({
  declarations: [CategoriesListComponent, CategoriesDialogComponent, AddCategoryComponent, CategoryDetailsComponent],
  imports: [
    LayoutModule,
    CommonModule
    ],
  exports: [
    CategoriesDialogComponent
  ]
})
export class CategoriesModule { }
