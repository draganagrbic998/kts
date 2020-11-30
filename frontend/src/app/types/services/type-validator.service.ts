import { Injectable } from '@angular/core';
import { AbstractControl, AsyncValidatorFn } from '@angular/forms';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { ValidationError } from 'src/app/utils/validation-error';
import { TypeService } from './type.service';

@Injectable({
  providedIn: 'root'
})
export class TypeValidatorService {

  constructor(
    private typeService: TypeService
  ) { }

  hasName(toBeUnique: boolean): AsyncValidatorFn{
    return (control: AbstractControl): Observable<null | ValidationError> => {
      return this.typeService.hasName({id: null, name: control.value}).pipe(
        map((response: boolean) => {
          if (toBeUnique){
            return !response ? null : {nameError: true};
          };
          return response ? null : {nameError: true};
        }), 
        catchError(() => of(null))
      );
    }
  }

}
