import { Injectable } from '@angular/core';
import { AbstractControl, AsyncValidatorFn } from '@angular/forms';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { ValidationError } from 'src/app/utils/validation-error';
import { CategoryService } from './category.service';

@Injectable({
  providedIn: 'root'
})
export class FormValidatorService {

  constructor(
    private categoryService: CategoryService
  ) { }

  hasName(): AsyncValidatorFn {
    return (control: AbstractControl): Observable<null | ValidationError> => {
      return this.categoryService.hasName({id: null, name: control.value}).pipe(
        map((response: boolean) => !response ? null : {nameError: true}),
        catchError(() => of(null))
      );
    }
  }

}
