import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterTestingModule } from '@angular/router/testing';
import { LayoutModule } from 'src/app/layout/layout.module';
import { CulturalService } from '../services/cultural.service';
import { UserFollowingService } from '../services/user-following.service';

import { CulturalDialogComponent } from './cultural-dialog.component';

describe('CulturalDialogComponent', () => {
  let component: CulturalDialogComponent;
  let fixture: ComponentFixture<CulturalDialogComponent>;

  beforeEach(async () => {
    const culturalOfferMock = {};
    const culturalServiceMock = {};
    const userFollowingService = {};
    const dialogRefMock = {};
    await TestBed.configureTestingModule({
      declarations: [ CulturalDialogComponent ],
      imports: [
        LayoutModule,
        RouterTestingModule,
        BrowserAnimationsModule
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
      providers: [
        {provide: MAT_DIALOG_DATA, useValue: culturalOfferMock},
        {provide: CulturalService, useValue: culturalServiceMock},
        {provide: UserFollowingService, useValue: userFollowingService},
        {provide: MatDialogRef, useValue: dialogRefMock}
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
