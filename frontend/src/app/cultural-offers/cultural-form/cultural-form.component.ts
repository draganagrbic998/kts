import { Component, ElementRef, EventEmitter, Inject, AfterViewInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable, of, Subject } from 'rxjs';
import places, { PlacesInstance } from 'places.js';
import { CulturalOffer } from 'src/app/models/cultural-offer';
import { CulturalService } from 'src/app/services/cultural-offer/cultural.service';
import { TypeService } from 'src/app/services/type/type.service';
import { ImageService } from 'src/app/services/image/image.service';
import { CulturalValidatorService } from 'src/app/validators/cultural-offer/cultural-validator.service';
import { TypeValidatorService } from 'src/app/validators/type/type-validator.service';
import { Geolocation } from 'src/app/models/geolocation';
import { Image } from 'src/app/models/image';
import { ERROR_MESSAGE, ERROR_SNACKBAR_OPTIONS, SNACKBAR_CLOSE, SUCCESS_SNACKBAR_OPTIONS } from 'src/app/constants/dialog';
import { ALGOLIA_API_ID, ALGOLIA_API_KEY } from 'src/app/constants/algolia';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import { AUTOCOMPLETE_DEBOUNCE, AUTOCOMPLETE_LENGTH } from 'src/app/constants/autocomplete';

@Component({
  selector: 'app-cultural-form',
  templateUrl: './cultural-form.component.html',
  styleUrls: ['./cultural-form.component.sass']
})
export class CulturalFormComponent implements AfterViewInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) public culturalOffer: CulturalOffer,
    private culturalService: CulturalService,
    private typeService: TypeService,
    private imageService: ImageService,
    private culturalValidator: CulturalValidatorService,
    private typeValidator: TypeValidatorService,
    public dialogRef: MatDialogRef<CulturalFormComponent>,
    private snackBar: MatSnackBar
  ) { }

  geolocation: Geolocation = {
    lat: this.culturalOffer.lat,
    lng: this.culturalOffer.lng
  };

  culturalForm: FormGroup = new FormGroup({
    type: new FormControl(this.culturalOffer.type || '', [Validators.required, Validators.pattern(new RegExp('\\S'))],
    [this.typeValidator.hasName(false)]),
    name: new FormControl(this.culturalOffer.name || '', [Validators.required, Validators.pattern(new RegExp('\\S'))],
    [this.culturalValidator.hasName(this.culturalOffer.id)]),
    location: new FormControl(this.culturalOffer.location || '',
    [Validators.required, this.culturalValidator.locationFound(this.geolocation)]),
    description: new FormControl(this.culturalOffer.description || '')
  });

  savePending = false;
  onSaved: EventEmitter<CulturalOffer> = new EventEmitter();
  image: Image = {upload: null, path: this.culturalOffer.image};

  typeFilters: Subject<string> = new Subject<string>();
  types: Observable<string[]> = this.typeFilters.pipe(
    debounceTime(AUTOCOMPLETE_DEBOUNCE),
    distinctUntilChanged(),
    switchMap((filter: string) => filter.length >= AUTOCOMPLETE_LENGTH ?
    this.typeService.filterNames(filter) : of([]))
  );

  @ViewChild('locationInput') locationInput: ElementRef<HTMLInputElement>;
  locationAutocomplete: PlacesInstance;

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

    if (this.culturalForm.invalid){
      return;
    }

    const formData: FormData = new FormData();
    const values: CulturalOffer = {...this.culturalOffer, ...this.geolocation, ...this.culturalForm.value};

    for (const i in values){
      if (i === 'id' || i === 'image'){
        continue;
      }
      formData.append(i, values[i]);
    }

    if (this.culturalOffer.id){
      formData.append('id', this.culturalOffer.id + '');
    }
    if (this.image.upload){
      formData.append('image', this.image.upload);
    }
    else if (this.image.path){
      formData.append('imagePath', this.image.path);
    }

    this.savePending = true;
    this.culturalService.save(formData).subscribe(
      (culturalOffer: CulturalOffer) => {
        this.snackBar.open('Offer successfully saved!', SNACKBAR_CLOSE, SUCCESS_SNACKBAR_OPTIONS);
        this.dialogRef.close();
        this.onSaved.emit(culturalOffer);
      },
      () => {
        this.savePending = false;
        this.snackBar.open(ERROR_MESSAGE, SNACKBAR_CLOSE, ERROR_SNACKBAR_OPTIONS);
      }
    );

  }

  ngAfterViewInit(): void{

    this.locationAutocomplete = places({
      container: this.locationInput.nativeElement,
      appId: ALGOLIA_API_ID,
      apiKey: ALGOLIA_API_KEY
    });

    this.locationAutocomplete.on('change', event => {
      this.geolocation.lat = event.suggestion.latlng.lat;
      this.geolocation.lng = event.suggestion.latlng.lng;
      this.culturalForm.get('location').setValue(event.suggestion.value);
      this.culturalForm.get('location').updateValueAndValidity();
    });

  }

}
