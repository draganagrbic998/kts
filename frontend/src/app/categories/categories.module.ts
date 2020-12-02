import { NgModule } from '@angular/core';

import { LayoutModule } from '../layout/layout.module';
import { CategoryDetailsComponent } from './category-details/category-details.component';
import { CategoryDialogComponent } from './category-dialog/category-dialog.component';
import { CategoryFormComponent } from './category-form/category-form.component';
import { CategoryListComponent } from './category-list/category-list.component';

@NgModule({
  declarations: [
    CategoryDetailsComponent,
    CategoryListComponent,
    CategoryFormComponent,
    CategoryDialogComponent
  ],
  imports: [
    LayoutModule
  ],
  exports: [
    CategoryDialogComponent
  ]
})
export class CategoriesModule { }
