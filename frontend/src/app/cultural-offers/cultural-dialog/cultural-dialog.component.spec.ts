import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LayoutModule } from 'src/app/layout/layout.module';

import { CulturalDialogComponent } from './cultural-dialog.component';

describe('CulturalDialogComponent', () => {
  let component: CulturalDialogComponent;
  let fixture: ComponentFixture<CulturalDialogComponent>;

  beforeEach(async () => {
    const culturalOfferMock = {};
    const dialogRefMock = {};
    await TestBed.configureTestingModule({
      declarations: [ CulturalDialogComponent ], 
      providers: [
        {provide: MAT_DIALOG_DATA, useValue: culturalOfferMock}, 
        {provide: MatDialogRef, useValue: dialogRefMock}
      ], 
      schemas: [CUSTOM_ELEMENTS_SCHEMA], 
      imports: [
        LayoutModule,
        BrowserAnimationsModule
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CulturalDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
