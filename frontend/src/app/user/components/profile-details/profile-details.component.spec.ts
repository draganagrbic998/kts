import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialogRef } from '@angular/material/dialog';
import { RouterTestingModule } from '@angular/router/testing';
import { LayoutModule } from 'src/app/layout/layout.module';
import { AuthService } from 'src/app/utils/services/auth.service';

import { ProfileDetailsComponent } from './profile-details.component';

describe('ProfileDetailsComponent', () => {
  let component: ProfileDetailsComponent;
  let fixture: ComponentFixture<ProfileDetailsComponent>;

  beforeEach(async () => {
    const authServiceMock = {
      getUser: jasmine.createSpy('getUser').and.returnValue({})
    };
    const dialogRefMock = {};
    await TestBed.configureTestingModule({
      declarations: [ ProfileDetailsComponent ],
      imports: [
        RouterTestingModule,
        LayoutModule
      ],
      providers: [
        {provide: AuthService, useValue: authServiceMock},
        {provide: MatDialogRef, useValue: dialogRefMock},
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA]
      
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfileDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
