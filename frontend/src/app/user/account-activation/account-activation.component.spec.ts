import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { SharedModule } from 'src/app/shared/shared.module';
import { UserService } from 'src/app/user/services/user.service';
import { AccountActivationComponent } from './account-activation.component';

describe('AccountActivationComponent', () => {
  let component: AccountActivationComponent;
  let fixture: ComponentFixture<AccountActivationComponent>;

  beforeEach(async () => {
    const userServiceMock = {
      activate: jasmine.createSpy('activate').and.returnValue(of(null))
    };
    const routeMock = {
      snapshot: {
        params: {
          code: null
        }
      }
    };
    await TestBed.configureTestingModule({
      declarations: [ AccountActivationComponent ],
      imports: [
        SharedModule,
        RouterTestingModule
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
      providers: [
        {provide: UserService, useValue: userServiceMock},
        {provide: ActivatedRoute, useValue: routeMock}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountActivationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
