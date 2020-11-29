import { NgModule } from '@angular/core';
import { LayoutModule } from '../layout/layout.module';
import { MatSelectModule} from '@angular/material/select';
import { TypeDetailsComponent } from './type-details/type-details.component';
import { TypeDialogComponent } from './type-dialog/type-dialog.component';
import { TypeListComponent } from './type-list/type-list.component';
import { TypeFormComponent } from './type-form/type-form.component';

@NgModule({
  declarations: [
    TypeDetailsComponent,
    TypeListComponent, 
    TypeDialogComponent, TypeFormComponent
  ],
  imports: [
    LayoutModule,
    MatSelectModule
  ],
  exports :[
    TypeDialogComponent
  ]
})
export class TypesModule { }
