import { Component, OnInit, Input,Output, EventEmitter } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmationDialogComponent } from 'src/app/layout/confirmation-dialog/confirmation-dialog.component';
import { ERROR_SNACKBAR_OPTIONS, SUCCESS_SNACKBAR_OPTIONS } from 'src/app/utils/constants';
import { CategoryService } from '../services/categories.service';
import { Category } from '../utils/category';

@Component({
  selector: 'app-category-details',
  templateUrl: './category-details.component.html',
  styleUrls: ['./category-details.component.sass']
})
export class CategoryDetailsComponent implements OnInit {

  constructor(
    private categoryService: CategoryService,
    private snackBar: MatSnackBar,
    public dialog: MatDialog) { }

  @Input() currentCategory: Category;
  @Output() onRefreshData: EventEmitter<string> = new EventEmitter();
  delPending: boolean = false;
  
  ngOnInit(): void {
  }

  delete(cat: Category): void {
    const dialog: MatDialogRef<ConfirmationDialogComponent> = this.dialog.open(ConfirmationDialogComponent, 
      {disableClose: true, panelClass: "no-padding", data: "Delete category?"});

      dialog.afterClosed().subscribe((result) => {
        if (result) {
          this.delPending = true;
    
          this.categoryService.delete(this.currentCategory.id).subscribe(
            () => {
              this.delPending = false;

              this.onRefreshData.emit("obrisana kategorija");
              console.log("obrisana kategorija");
        
              this.snackBar.open("You have successfully deleted this category!", "Close", SUCCESS_SNACKBAR_OPTIONS);
            }, 
            () => {
              this.delPending = false;
              this.snackBar.open("Can't delete this category because there are cultural offers that have this category! Try again.", 
              "Close", ERROR_SNACKBAR_OPTIONS);
            }
          )
        }
      });
    }

}
