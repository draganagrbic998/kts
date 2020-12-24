import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { of } from 'rxjs';
import { SharedModule } from 'src/app/shared/shared.module';
import { CulturalService } from 'src/app/cultural-offers/services/cultural.service';
import { TypeService } from 'src/app/cats-types/services/type.service';
import { CulturalValidatorService } from 'src/app/cultural-offers/services/cultural-validator.service';
import { TypeValidatorService } from 'src/app/cats-types/services/type-validator.service';
import { CulturalFormComponent } from './cultural-form.component';

describe('CulturalFormComponent', () => {
  let component: CulturalFormComponent;
  let fixture: ComponentFixture<CulturalFormComponent>;

  beforeEach(async () => {
    const culturalOfferMock = {};
    const culturalServiceMock = {};
    const typeServiceMock = {};
    const culturalValidatorMock = {
      hasName: jasmine.createSpy('hasName').and.returnValue(() => of(null)),
      locationFound: jasmine.createSpy('locationFound').and.returnValue(of(null))
    };
    const typeValidatorMock = {
      hasName: jasmine.createSpy('hasName').and.returnValue(() => of(null)),
    };
    const dialogRefMock = {};
    await TestBed.configureTestingModule({
      declarations: [ CulturalFormComponent ],
      imports: [
        SharedModule,
        BrowserAnimationsModule
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
      providers: [
        {provide: MAT_DIALOG_DATA, useValue: culturalOfferMock},
        {provide: CulturalService, useValue: culturalServiceMock},
        {provide: TypeService, useValue: typeServiceMock},
        {provide: CulturalValidatorService, culturalValidatorMock},
        {provide: TypeValidatorService, useValue: typeValidatorMock},
        {provide: MatDialogRef, useValue: dialogRefMock}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CulturalFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
