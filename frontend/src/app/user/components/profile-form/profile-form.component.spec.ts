import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { FormValidatorService } from '../../services/form-validator.service';

import { ProfileFormComponent } from './profile-form.component';

import { UserService } from '../../services/user.service';
import { AuthService } from 'src/app/utils/services/auth.service';
import { ImageService } from 'src/app/utils/services/image.service';
import { of } from 'rxjs';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { LayoutModule } from 'src/app/layout/layout.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

describe('ProfileFormComponent', () => {
  let component: ProfileFormComponent;
  let fixture: ComponentFixture<ProfileFormComponent>;

  beforeEach(async () => {
    const authServiceMock = {
      getUser: jasmine.createSpy('getUser').and.returnValue({}),
    };
    const userServiceMock = {};
    const imageServiceMock = {};
    const formValidatorMock = {
      hasEmail: jasmine.createSpy('hasEmail').and.returnValue(() => of(null)),
      newPasswordConfirmed: jasmine.createSpy('newPasswordConfirmed').and.returnValue(() => null)
    };
    await TestBed.configureTestingModule({
      declarations: [ ProfileFormComponent ],
      imports: [
        MatSnackBarModule,
        ReactiveFormsModule,
        LayoutModule,
        BrowserAnimationsModule
      ],
      providers: [
        {provide: AuthService, useValue: authServiceMock},
        {provide: UserService, useValue: userServiceMock},
        {provide: ImageService, useValue: imageServiceMock},
        {provide: FormValidatorService, useValue: formValidatorMock}
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfileFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
