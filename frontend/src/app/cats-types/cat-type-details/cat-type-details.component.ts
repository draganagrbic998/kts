import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { DIALOG_OPTIONS } from 'src/app/constants/dialog';
import { DeleteConfirmationComponent } from 'src/app/layout/delete-confirmation/delete-confirmation.component';
import { Category } from 'src/app/models/category';
import { Type } from 'src/app/models/type';
import { CategoryService } from 'src/app/services/category/category.service';
import { TypeService } from 'src/app/services/type/type.service';

@Component({
  selector: 'app-cat-type-details',
  templateUrl: './cat-type-details.component.html',
  styleUrls: ['./cat-type-details.component.sass']
})
export class CatTypeDetailsComponent implements OnInit {

  constructor(
    private categoryService: CategoryService,
    private typeService: TypeService,
    private dialog: MatDialog
  ) { }

  @Input() catType: Category | Type;
  @Output() deleted: EventEmitter<null> = new EventEmitter();

  delete(): void {
    const service = (this.catType as Type).category ? this.typeService : this.categoryService;
    const options = {...DIALOG_OPTIONS, ...{data: () => service.delete(this.catType.id)}};
    const dialog: MatDialogRef<DeleteConfirmationComponent> = this.dialog.open(DeleteConfirmationComponent, options);
    dialog.componentInstance.deleted.subscribe(
      () => {
        this.deleted.emit();
      }
    );
  }

  ngOnInit(): void {
  }

}
