import { NgModule } from '@angular/core';
import { LayoutModule } from '../layout/layout.module';
import { TypeDetailsComponent } from './type-details/type-details.component';
import { TypeDialogComponent } from './type-dialog/type-dialog.component';
import { TypeListComponent } from './type-list/type-list.component';

@NgModule({
  declarations: [
    TypeDetailsComponent,
    TypeListComponent, 
    TypeDialogComponent
  ],
  imports: [
    LayoutModule
  ],
  exports :[
    TypeDialogComponent
  ]
})
export class TypesModule { }
