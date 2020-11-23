import { Component, OnInit , Input, Output} from '@angular/core';
import { Category } from '../utils/category';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-categories-list',
  templateUrl: './categories-list.component.html',
  styleUrls: ['./categories-list.component.sass']
})
export class CategoriesListComponent implements OnInit {

  constructor() { }

  @Input() title: string;
  @Input() categories: Category[];
  @Input() fetchPending: boolean;

  ngOnInit(): void {
  }

}
