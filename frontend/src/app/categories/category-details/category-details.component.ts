import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { DeleteConfirmationComponent } from 'src/app/layout/delete-confirmation/delete-confirmation.component';
import { DIALOG_OPTIONS } from 'src/app/utils/constants';
import { CategoryService } from '../services/category.service';
import { Category } from '../category';

@Component({
  selector: 'app-category-details',
  templateUrl: './category-details.component.html',
  styleUrls: ['./category-details.component.sass']
})
export class CategoryDetailsComponent implements OnInit {

  constructor(
    private categoryService: CategoryService,
    private dialog: MatDialog
  ) { }

  @Input() category: Category;
  @Output() deleted: EventEmitter<null> = new EventEmitter();

  delete(): void {
    const options = {...DIALOG_OPTIONS, ...{data: () => this.categoryService.delete(this.category.id)}};
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
