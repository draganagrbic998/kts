import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CategoryService } from 'src/app/categories/services/category.service';
import { Category } from 'src/app/categories/utils/category';
import { ImageService } from 'src/app/services/image.service';
import { ERROR_MESSAGE, ERROR_SNACKBAR_OPTIONS, SNACKBAR_CLOSE, SUCCESS_SNACKBAR_OPTIONS } from 'src/app/utils/constants';
import { Image } from 'src/app/utils/image';
import { TypeValidatorService } from '../services/type-validator.service';
import { TypeService } from '../services/type.service';

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
    private snackBar: MatSnackBar
  ) { }

  @Output() onAdded: EventEmitter<null> = new EventEmitter();
  categories: Category[] = [];
  image: Image = {path: "", upload: null};
  savePending: boolean = false;
  typeForm: FormGroup = new FormGroup({
    name: new FormControl("", [Validators.required, Validators.pattern(new RegExp("\\S"))],[this.typeValidator.hasName(true)]),
    category: new FormControl("", [Validators.required])
  });
  
  addImage(upload: Blob): void{
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
    this.categoryService.all().subscribe(
      (categories: Category[]) => {
        this.categories = categories;
      }
    );
  }

  save(): void{
    if (this.typeForm.invalid){
      return;
    }

    const formData: FormData = new FormData();

    for (const i in this.typeForm.controls){
      formData.append(i, this.typeForm.get(i).value);
    }

    if (this.image.upload){
      formData.append("image", this.image.upload);
    }  

    this.savePending = true;
    this.typeService.save(formData).subscribe(
      () => {
        this.savePending = false;
        this.onAdded.emit();
        this.snackBar.open("Type successfully added!", SNACKBAR_CLOSE, SUCCESS_SNACKBAR_OPTIONS);
      }, 
      () => {
        this.savePending = false;
        this.snackBar.open(ERROR_MESSAGE, SNACKBAR_CLOSE, ERROR_SNACKBAR_OPTIONS);
      }
    )
  }

  ngOnInit(): void {
    this.fetchCategories();
  }

}
