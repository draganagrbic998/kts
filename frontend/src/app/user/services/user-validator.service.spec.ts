import { TestBed } from '@angular/core/testing';

import { UserValidatorService } from './user-validator.service';
import { UserService } from './user.service';

describe('UserValidatorService', () => {
  let service: UserValidatorService;

  beforeEach(() => {
    const userServiceMock = {};
    TestBed.configureTestingModule({
      providers: [
        {provide: UserService, useValue: userServiceMock}
      ]
    });
    service = TestBed.inject(UserValidatorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
