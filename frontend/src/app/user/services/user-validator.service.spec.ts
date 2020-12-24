import { TestBed } from '@angular/core/testing';

import { UserService } from 'src/app/user/services/user.service';
import { UserValidatorService } from './user-validator.service';

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
