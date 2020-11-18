import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';

import { FormValidatorService } from './form-validator.service';
import { AuthService } from 'src/app/utils/services/auth.service';

describe('FormValidatorService', () => {
  let service: FormValidatorService;

  beforeEach(() => {
    const authServiceMock = {};
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule
      ],
      providers: [
        {provide: AuthService, useValue: authServiceMock},
      ]
    });
    service = TestBed.inject(FormValidatorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
