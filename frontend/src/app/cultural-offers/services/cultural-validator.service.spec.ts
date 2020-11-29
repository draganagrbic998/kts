import { TestBed } from '@angular/core/testing';

import { CulturalValidatorService } from './cultural-validator.service';

describe('CulturalValidatorService', () => {
  let service: CulturalValidatorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CulturalValidatorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
