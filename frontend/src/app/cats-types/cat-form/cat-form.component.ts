import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ERROR_MESSAGE, ERROR_SNACKBAR_OPTIONS, SNACKBAR_CLOSE, SUCCESS_SNACKBAR_OPTIONS } from 'src/app/constants/dialog';
import { CategoryService } from 'src/app/services/category/category.service';
import { CategoryValidatorService } from 'src/app/validators/category/category-validator.service';

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

  categoryForm: FormGroup = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.pattern(new RegExp('\\S'))], [this.categoryValidator.hasName(true)])
  });
  savePending = false;
  @Output() saved: EventEmitter<null> = new EventEmitter();

  save(): void{
    if (this.categoryForm.invalid){
      return;
    }
    this.savePending = true;
    this.categoryService.save(this.categoryForm.value).subscribe(
      () => {
        this.savePending = false;
        this.saved.emit();
        this.snackBar.open('Category successfully added!', SNACKBAR_CLOSE, SUCCESS_SNACKBAR_OPTIONS);
        this.categoryForm.reset();
      },
      () => {
        this.savePending = false;
        this.snackBar.open(ERROR_MESSAGE, SNACKBAR_CLOSE, ERROR_SNACKBAR_OPTIONS);
      }
    );
  }

  ngOnInit(): void {
  }

}