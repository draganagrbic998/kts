import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable, of, Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import { AUTOCOMPLETE_DEBOUNCE, AUTOCOMPLETE_LENGTH } from 'src/app/constants/autocomplete';
import { ERROR_MESSAGE, ERROR_SNACKBAR_OPTIONS, SNACKBAR_CLOSE, SUCCESS_SNACKBAR_OPTIONS } from 'src/app/constants/dialog';
import { Image } from 'src/app/models/image';
import { CategoryService } from 'src/app/services/category/category.service';
import { ImageService } from 'src/app/services/image/image.service';
import { TypeService } from 'src/app/services/type/type.service';
import { CategoryValidatorService } from 'src/app/validators/category/category-validator.service';
import { TypeValidatorService } from 'src/app/validators/type/type-validator.service';

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

  categoryFilters: Subject<string> = new Subject<string>();
  categories: Observable<string[]> = this.categoryFilters.pipe(
    debounceTime(AUTOCOMPLETE_DEBOUNCE),
    distinctUntilChanged(),
    switchMap((filter: string) => filter.length >= AUTOCOMPLETE_LENGTH ?
    this.categoryService.filterNames(filter) : of([]))
  );

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
        this.typeForm.reset();
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
