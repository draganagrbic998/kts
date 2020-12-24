import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SNACKBAR_ERROR_MESSAGE, SNACKBAR_ERROR_OPTIONS, SNACKBAR_CLOSE, SNACKBAR_SUCCESS_OPTIONS } from 'src/app/constants/snackbar';
import { CategoryService } from 'src/app/cats-types/services/category.service';
import { CategoryValidatorService } from 'src/app/cats-types/services/category-validator.service';

@Component({
  selector: 'app-cat-form',
  templateUrl: './cat-form.component.html',
  styleUrls: ['./cat-form.component.sass']
})
export class CatFormComponent implements OnInit {

  constructor(
    private categoryService: CategoryService,
    private categoryValidator: CategoryValidatorService,
    private snackBar: MatSnackBar
  ) { }

  savePending = false;
  categoryForm: FormGroup = new FormGroup({
    name: new FormControl('', [Validators.required,
      Validators.pattern(new RegExp('\\S'))],
      [this.categoryValidator.hasName(true)])
  });

  save(): void{
    if (this.categoryForm.invalid){
      return;
    }
    this.savePending = true;
    this.categoryService.save(this.categoryForm.value).subscribe(
      () => {
        this.savePending = false;
        this.snackBar.open('Category successfully added!', SNACKBAR_CLOSE, SNACKBAR_SUCCESS_OPTIONS);
        this.categoryForm.reset();
        this.categoryService.announceRefreshData();
      },
      () => {
        this.savePending = false;
        this.snackBar.open(SNACKBAR_ERROR_MESSAGE, SNACKBAR_CLOSE, SNACKBAR_ERROR_OPTIONS);
      }
    );
  }

  ngOnInit(): void {
  }

}
