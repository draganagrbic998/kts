import { TestBed } from '@angular/core/testing';

import { FormValidatorService } from './form-validator.service';
import { UserService } from './user.service';

describe('FormValidatorService', () => {
  let service: FormValidatorService;

  beforeEach(() => {
    const userServiceMock = {};
    TestBed.configureTestingModule({
      providers: [
        {provide: UserService, useValue: userServiceMock}
      ]
    });
    service = TestBed.inject(FormValidatorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
