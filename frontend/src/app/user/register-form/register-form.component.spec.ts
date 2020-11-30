import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { of } from 'rxjs';
import { LayoutModule } from 'src/app/layout/layout.module';
import { UserValidatorService } from '../services/user-validator.service';
import { UserService } from '../services/user.service';

import { RegisterFormComponent } from './register-form.component';

describe('RegisterFormComponent', () => {
  let component: RegisterFormComponent;
  let fixture: ComponentFixture<RegisterFormComponent>;

  beforeEach(async () => {
    const userServiceMock = {};
    const formValidatorMock = {
      hasEmail: jasmine.createSpy('hasEmail').and.returnValue(() => of(null)),
      passwordConfirmed: jasmine.createSpy('passwordConfirmed').and.returnValue(() => null)
    };
    await TestBed.configureTestingModule({
      declarations: [ RegisterFormComponent ],
      imports: [
        LayoutModule,
        BrowserAnimationsModule
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
      providers: [
        {provide: UserService, useValue: userServiceMock}, 
        {provide: UserValidatorService, useValue: formValidatorMock}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
