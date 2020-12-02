import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ERROR_MESSAGE, ERROR_SNACKBAR_OPTIONS, SNACKBAR_CLOSE, SUCCESS_SNACKBAR_OPTIONS } from 'src/app/utils/constants';
import { CategoryService } from '../services/category.service';
import { CategoryValidatorService } from '../services/category-validator.service';

@Component({
  selector: 'app-category-form',
  templateUrl: './category-form.component.html',
  styleUrls: ['./category-form.component.sass']
})
export class CategoryFormComponent implements OnInit {

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
