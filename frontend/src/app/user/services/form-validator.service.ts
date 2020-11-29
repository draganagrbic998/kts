import { Injectable } from '@angular/core';
import { AbstractControl, AsyncValidatorFn, ValidatorFn } from '@angular/forms';
import { Observable, of } from 'rxjs';
import { ValidationError } from 'src/app/utils/validation-error';
import { UserService } from './user.service';
import { catchError, map } from 'rxjs/operators';
import { CategoryService } from 'src/app/categories/services/category.service';
import { TypeService } from 'src/app/types/services/type.service';

@Injectable({
  providedIn: 'root'
})
export class FormValidatorService {

  constructor(
    private userService: UserService,
    private categoryService: CategoryService,
    private typeService: TypeService
  ) { }

  passwordConfirmed(): ValidatorFn{
    return (control: AbstractControl): null | ValidationError => {
      const passwordConfirmed: boolean = control.parent ? 
      control.value === control.parent.get('password').value : true;
      return passwordConfirmed ? null : {passwordError: true};
    }
  }
  
  newPasswordConfirmed(): ValidatorFn {
    return (control: AbstractControl): null | ValidationError => {
      if ((control.get('newPassword').value || control.get('newPasswordConfirmed').value) && !control.get('oldPassword').value){
        return {oldPasswordError: true};
      }
      if (control.get('newPassword').value !== control.get('newPasswordConfirmed').value){
        return {newPasswordError: true};
      }
      return null;
    }
  }

  hasEmail(id: number): AsyncValidatorFn {
    return (control: AbstractControl): Observable<null | ValidationError> => {
      return this.userService.hasEmail({id: id, name: control.value}).pipe(
        map((response: boolean) => !response ? null : {emailError: true}),
        catchError(() => of(null))
      );
    }
  }

  hasName(): AsyncValidatorFn {
    return (control: AbstractControl): Observable<null | ValidationError> => {
      return this.categoryService.hasName({id: null, name: control.value}).pipe(
        map((response: boolean) => !response ? null : {nameError: true}),
        catchError(() => of(null))
      );
    }
  }
  hasNameType(): AsyncValidatorFn {
    return (control: AbstractControl): Observable<null | ValidationError> => {
      return this.typeService.hasName({id: null, name: control.value}).pipe(
        map((response: boolean) => !response ? null : {nameError: true}),
        catchError(() => of(null))
      );
    }
  }
  
}
