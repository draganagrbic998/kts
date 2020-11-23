import { Component, OnInit } from '@angular/core';
import { Category } from '../utils/category';
import { CategoryService} from '../services/categories.service'
import { MatSnackBar } from '@angular/material/snack-bar';
import { ERROR_SNACKBAR_OPTIONS, SUCCESS_SNACKBAR_OPTIONS } from 'src/app/utils/constants';

@Component({
  selector: 'app-categories-dialog',
  templateUrl: './categories-dialog.component.html',
  styleUrls: ['./categories-dialog.component.sass']
})
export class CategoriesDialogComponent implements OnInit {

  constructor(
    private categoryService: CategoryService,
    private snackBar: MatSnackBar
  ) { }
  selectedTag: number = 0;
  fetchPending: boolean = true;
  categories: Category[] = [];
  title: string[] = [
    "Add Category", 
    "All Categories"
  ];
  ngOnInit(): void {
    this.fetchData();
  }

  changeTab(index: number): void{
    this.selectedTag = index;
  }
  fetchData() : void{
      //dobavi sve kategorije sa servera
      this.categoryService.getAllCategories().subscribe(
        (cats:Category[]) => {
          this.categories = cats;
          this.fetchPending = false;
        }, 
        () => {
          this.fetchPending = false;
          this.snackBar.open("An error occured! Try again.", 
          "Close", ERROR_SNACKBAR_OPTIONS);
        }
      )
    }
  

}
