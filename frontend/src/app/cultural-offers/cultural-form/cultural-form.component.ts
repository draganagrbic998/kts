import { Component, ElementRef, EventEmitter, Inject, AfterViewInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { ImageService } from 'src/app/services/image.service';
import { FormValidatorService } from 'src/app/types/services/form-validator.service';
import { TypeService } from 'src/app/types/services/type.service';
import { ERROR_MESSAGE, ERROR_SNACKBAR_OPTIONS, SNACKBAR_CLOSE, SUCCESS_SNACKBAR_OPTIONS } from 'src/app/utils/constants';
import { Image } from 'src/app/utils/image';
import { CulturalValidatorService } from '../services/cultural-validator.service';
import { CulturalService } from '../services/cultural.service';
import { CulturalOffer } from '../utils/cultural-offer';
import { Geolocation } from '../utils/geolocation';
import { ALGOLIA_API_ID, ALGOLIA_API_KEY } from '../utils/constants';
import places, { PlacesInstance } from 'places.js';

@Component({
  selector: 'app-cultural-form',
  templateUrl: './cultural-form.component.html',
  styleUrls: ['./cultural-form.component.sass']
})
export class CulturalFormComponent implements AfterViewInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) public culturalOffer: CulturalOffer, 
    private culturalService: CulturalService, 
    private imageService: ImageService, 
    private typeValidator: FormValidatorService,
    public dialogRef: MatDialogRef<CulturalFormComponent>, 
    private snackBar: MatSnackBar,
    private typeService: TypeService,
    private culturalValidator: CulturalValidatorService
  ) { }

  geolocation: Geolocation = {
    lat: this.culturalOffer.lat, 
    lng: this.culturalOffer.lng
  }

  culturalForm: FormGroup = new FormGroup({
    type: new FormControl(this.culturalOffer.type || '', [Validators.required, Validators.pattern(new RegExp("\\S"))], 
    [this.typeValidator.hasName(false)]), 
    name: new FormControl(this.culturalOffer.name || '', [Validators.required, Validators.pattern(new RegExp("\\S"))], 
    [this.culturalValidator.hasName(this.culturalOffer.id)]), 
    location: new FormControl(this.culturalOffer.location || '', [Validators.required, this.culturalValidator.locationFound(this.geolocation)]), 
    description: new FormControl(this.culturalOffer.description || '')
  });

  image: Image = {upload: null, path: this.culturalOffer.image};
  savePending: boolean = false;
  onSaved: EventEmitter<CulturalOffer> = new EventEmitter();

  types: Observable<string[]>;
  @ViewChild('locationInput') locationInput: ElementRef<HTMLInputElement>;
  locationAutocomplete: PlacesInstance;

  fetchTypes(): void{
    const value: string = this.culturalForm.get('type').value.trim().toLowerCase();
    this.types = this.typeService.filterNames(value);
  }

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

  save(): void{

    if (this.culturalForm.invalid){
      return;
    }

    const formData: FormData = new FormData();
    const values: CulturalOffer = {...this.culturalOffer, ...this.geolocation, ...this.culturalForm.value};
    
    for (const i in values){
      if (i === "id" || i === "image"){
        continue;
      }
      formData.append(i, values[i]);
    }

    if (this.culturalOffer.id){
      formData.append("id", this.culturalOffer.id + "");
    }

    if (this.image.upload){
      formData.append("image", this.image.upload);
    }  

    else if (this.image.path){
      formData.append("imagePath", this.image.path);
    }
    
    this.savePending = true;
    this.culturalService.save(formData).subscribe(
      (culturalOffer: CulturalOffer) => {
        this.dialogRef.close();
        this.snackBar.open("Cultural offer has been successfully saved!", 
        SNACKBAR_CLOSE, SUCCESS_SNACKBAR_OPTIONS);
        this.onSaved.emit(culturalOffer);
      }, 
      () => {
        this.savePending = false;
        this.snackBar.open(ERROR_MESSAGE, SNACKBAR_CLOSE, ERROR_SNACKBAR_OPTIONS);
      }
    )

  }

  ngAfterViewInit(): void{
  
    this.locationAutocomplete = places({
      container: this.locationInput.nativeElement,
      appId: ALGOLIA_API_ID,
      apiKey: ALGOLIA_API_KEY 
    });

    this.locationAutocomplete.on("change", event => {
      this.geolocation.lat = event.suggestion.latlng["lat"];
      this.geolocation.lng = event.suggestion.latlng["lng"];
      this.culturalForm.get('location').updateValueAndValidity();
    });
    
  }


}
