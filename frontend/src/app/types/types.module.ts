import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LayoutModule } from '../layout/layout.module';
import { TypesDialogComponent } from './types-dialog/types-dialog.component';
import { TypesListComponent } from './types-list/types-list.component';
import { TypeDetailsComponent } from './type-details/type-details.component';



@NgModule({
  declarations: [TypesDialogComponent, TypesListComponent, TypeDetailsComponent],
  imports: [
    CommonModule,
    LayoutModule
  ],
  exports :[TypesDialogComponent]
})
export class TypesModule { }
