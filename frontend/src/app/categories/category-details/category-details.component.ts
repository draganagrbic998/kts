import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { DeleteConfirmationComponent } from 'src/app/layout/delete-confirmation/delete-confirmation.component';
import { DIALOG_OPTIONS } from 'src/app/utils/constants';
import { CategoryService } from '../services/category.service';
import { Category } from '../utils/category';

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
  @Output() onDeleted: EventEmitter<number> = new EventEmitter();
  
  delete(): void {
    const options = {...DIALOG_OPTIONS, ...{data: () => this.categoryService.delete(this.category.id)}}
    const dialog: MatDialogRef<DeleteConfirmationComponent> = this.dialog.open(DeleteConfirmationComponent, options);
    dialog.componentInstance.onDeleted.subscribe(
      () => {
        this.onDeleted.emit(this.category.id);
      }
    );
  }
  
  ngOnInit(): void {
  }

}
