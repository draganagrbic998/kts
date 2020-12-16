import { Injectable } from '@angular/core';
import { AbstractControl, AsyncValidatorFn, ValidatorFn } from '@angular/forms';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Geolocation } from 'src/app/models/geolocation';
import { ValidationError } from 'src/app/models/validation-error';
import { CulturalService } from 'src/app/services/cultural-offer/cultural.service';
import { Flag } from 'src/app/models/flag';

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
        map((response: Flag) => !response.flag ? null : {nameError: true}),
        catchError(() => of(null))
      );
    };
  }

}
