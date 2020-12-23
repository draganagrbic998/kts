import { Injectable } from '@angular/core';
import { AbstractControl, AsyncValidatorFn, ValidatorFn } from '@angular/forms';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { ValidationError } from 'src/app/models/validation-error';
import { UserService } from 'src/app/services/user/user.service';

@Injectable({
  providedIn: 'root'
})
export class UserValidatorService {

  constructor(
    private userService: UserService
  ) { }

  passwordConfirmed(): ValidatorFn{
    return (control: AbstractControl): null | ValidationError => {
      const passwordConfirmed: boolean = control.parent ?
      control.value === control.parent.get('password').value : true;
      return passwordConfirmed ? null : {passwordError: true};
    };
  }

  newPasswordConfirmed(): ValidatorFn {
    return (control: AbstractControl): null | ValidationError => {
      if ((control.get('newPassword').value || control.get('newPasswordConfirmation').value) && !control.get('oldPassword').value){
        return {oldPasswordError: true};
      }
      if (control.get('newPassword').value !== control.get('newPasswordConfirmation').value){
        return {newPasswordError: true};
      }
      return null;
    };
  }

  hasEmail(id: number): AsyncValidatorFn {
    return (control: AbstractControl): Observable<null | ValidationError> => {
      return this.userService.hasEmail({id, name: control.value}).pipe(
        map((response: boolean) => !response ? null : {emailError: true}),
        catchError(() => of(null))
      );
    };
  }

}
