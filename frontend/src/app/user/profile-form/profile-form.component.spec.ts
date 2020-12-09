import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { of } from 'rxjs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ProfileFormComponent } from './profile-form.component';
import { LayoutModule } from 'src/app/layout/layout.module';
import { AuthService } from 'src/app/services/auth/auth.service';
import { UserService } from 'src/app/services/user/user.service';
import { UserValidatorService } from 'src/app/validators/user/user-validator.service';

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
