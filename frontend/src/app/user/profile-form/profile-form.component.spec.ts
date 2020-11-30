import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileFormComponent } from './profile-form.component';

import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { LayoutModule } from 'src/app/layout/layout.module';
import { UserService } from '../services/user.service';
import { UserValidatorService } from '../services/user-validator.service';
import { AuthService } from 'src/app/services/auth.service';
import { of } from 'rxjs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

describe('ProfileFormComponent', () => {
  let component: ProfileFormComponent;
  let fixture: ComponentFixture<ProfileFormComponent>;

  beforeEach(async () => {
    const authServiceMock = {
      getUser: jasmine.createSpy('getUser').and.returnValue({})
    };
    const userServiceMock = {};
    const formValidatorMock = {
      hasEmail: jasmine.createSpy('hasEmail').and.returnValue(() => of(null)),
      newPasswordConfirmed: jasmine.createSpy('newPasswordConfirmed').and.returnValue(() => null)
    };
    await TestBed.configureTestingModule({
      declarations: [ ProfileFormComponent ],
      imports: [
        LayoutModule, 
        BrowserAnimationsModule
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
      providers: [
        {provide: AuthService, useValue: authServiceMock},
        {provide: UserService, useValue: userServiceMock}, 
        {provide: UserValidatorService, useValue: formValidatorMock}
      ]
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
