import { Component, OnInit , Input, Output, EventEmitter} from '@angular/core';
import { Category } from '../utils/category';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmationDialogComponent } from 'src/app/layout/confirmation-dialog/confirmation-dialog.component';
import { CategoryService } from '../services/categories.service';
import { ERROR_SNACKBAR_OPTIONS, SUCCESS_SNACKBAR_OPTIONS } from 'src/app/utils/constants';

@Component({
  selector: 'app-categories-list',
  templateUrl: './categories-list.component.html',
  styleUrls: ['./categories-list.component.sass']
})
export class CategoriesListComponent implements OnInit {

  constructor(
    private categoryService: CategoryService,
    private snackBar: MatSnackBar,
    public dialog: MatDialog) { }

  @Input() title: string;
  @Input() categories: Category[];
  @Input() fetchPending: boolean;
  @Output() onRefreshData: EventEmitter<string> = new EventEmitter();

  

  ngOnInit(): void {
  }

  refreshData(): void{
    this.onRefreshData.emit("obrisana kategorija");
  }

  

}
