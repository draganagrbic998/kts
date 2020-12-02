import { TestBed } from '@angular/core/testing';

import { TypeValidatorService } from './type-validator.service';
import { TypeService } from './type.service';

describe('TypeValidatorService', () => {
  let service: TypeValidatorService;

  beforeEach(() => {
    const typeServiceMock = {};
    TestBed.configureTestingModule({
      providers: [
        {provide: TypeService, useValue: typeServiceMock},
      ]
    });
    service = TestBed.inject(TypeValidatorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
