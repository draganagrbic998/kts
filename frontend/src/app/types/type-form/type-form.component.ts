import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CategoryService } from 'src/app/categories/services/category.service';
import { ImageService } from 'src/app/services/image.service';
import { ERROR_MESSAGE, ERROR_SNACKBAR_OPTIONS, SNACKBAR_CLOSE, SUCCESS_SNACKBAR_OPTIONS } from 'src/app/utils/constants';
import { Image } from 'src/app/utils/image';
import { TypeValidatorService } from '../services/type-validator.service';
import { TypeService } from '../services/type.service';
import { Observable } from 'rxjs';
import { CategoryValidatorService } from 'src/app/categories/services/category-validator.service';

@Component({
  selector: 'app-type-form',
  templateUrl: './type-form.component.html',
  styleUrls: ['./type-form.component.sass']
})
export class TypeFormComponent implements OnInit {

  constructor(
    private typeService: TypeService,
    private categoryService: CategoryService,
    private imageService: ImageService,
    private typeValidator: TypeValidatorService,
    private categoryValidator: CategoryValidatorService,
    private snackBar: MatSnackBar
  ) { }

  typeForm: FormGroup = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.pattern(new RegExp('\\S'))], [this.typeValidator.hasName(true)]),
    category: new FormControl('', [Validators.required, Validators.pattern(new RegExp('\\S'))], [this.categoryValidator.hasName(false)])
  });
  savePending = false;
  @Output() saved: EventEmitter<null> = new EventEmitter();
  image: Image = {path: '', upload: null};
  categories: Observable<string[]>;

  changeImage(upload: Blob): void{
    this.image.upload = upload;
    this.imageService.getBase64(upload)
    .then((image: string) => {
      this.image.path = image;
    });
  }

  removeImage(): void{
    this.image.upload = null;
    this.image.path = null;
  }

  fetchCategories(): void{
    const value: string = this.typeForm.get('category').value.trim().toLowerCase();
    this.categories = this.categoryService.filterNames(value);
  }

  save(): void{
    if (this.typeForm.invalid){
      return;
    }

    const formData: FormData = new FormData();
    Object.keys(this.typeForm.value).forEach(key => {
      formData.append(key, this.typeForm.value[key]);
    });
    if (this.image.upload){
      formData.append('placemarkIcon', this.image.upload);
    }

    this.savePending = true;
    this.typeService.save(formData).subscribe(
      () => {
        this.savePending = false;
        this.saved.emit();
        this.snackBar.open('Type successfully added!', SNACKBAR_CLOSE, SUCCESS_SNACKBAR_OPTIONS);
      },
      () => {
        this.savePending = false;
        this.snackBar.open(ERROR_MESSAGE, SNACKBAR_CLOSE, ERROR_SNACKBAR_OPTIONS);
      }
    );
  }

  ngOnInit(): void {
    this.fetchCategories();
  }

}
