import { Component, OnInit,Input, Output, EventEmitter } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ERROR_SNACKBAR_OPTIONS, SUCCESS_SNACKBAR_OPTIONS } from 'src/app/utils/constants';
import { CategoryService} from '../services/categories.service'


@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.sass']
})
export class AddCategoryComponent implements OnInit {

  constructor(
    private categoryService: CategoryService,
    private snackBar: MatSnackBar) { }

  @Input() title: string;
  @Output() onRefreshData: EventEmitter<string> = new EventEmitter();

  addPending: boolean = false;
  addForm: FormGroup = new FormGroup({
    name: new FormControl("", [Validators.required, Validators.pattern(new RegExp("\\S"))])
  });
  ngOnInit(): void {
  }

  refreshData(): void{
    this.onRefreshData.emit("dodata nova kategorija");
  }

  add(): void{
    if (this.addForm.invalid){
      return;
    }

    this.addPending = true;
    this.categoryService.addCategory(this.addForm.value).subscribe(
      () => {
        this.addPending = false;
        this.refreshData();
        this.snackBar.open("Category successfully added!", "Close", SUCCESS_SNACKBAR_OPTIONS);
      }, 
      () => {
        this.addPending = false;
        this.snackBar.open("Category already exists! Try again.", 
        "Close", ERROR_SNACKBAR_OPTIONS);
      }
    )
  }

}
