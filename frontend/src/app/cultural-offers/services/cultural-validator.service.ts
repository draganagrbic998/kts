import { Injectable } from '@angular/core';
import { AbstractControl, AsyncValidatorFn, ValidatorFn } from '@angular/forms';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { ValidationError } from 'src/app/utils/validation-error';
import { Geolocation } from '../utils/geolocation';
import { CulturalService } from './cultural.service';

@Injectable({
  providedIn: 'root'
})
export class CulturalValidatorService {

  constructor(
    private culturalService: CulturalService
  ) { }

  locationFound(geolocation: Geolocation): ValidatorFn{
    return (): null | ValidationError => {
      const empty = [null, undefined];
      return (empty.includes(geolocation.lat) || empty.includes(geolocation.lng)) ? {locationError: true} : null;
    };
  }

  hasName(id: number): AsyncValidatorFn{
    return (control: AbstractControl): Observable<null | ValidationError> => {
      return this.culturalService.hasName({id, name: control.value}).pipe(
        map((response: boolean) => !response ? null : {nameError: true}),
        catchError(() => of(null))
      );
    };
  }

}
