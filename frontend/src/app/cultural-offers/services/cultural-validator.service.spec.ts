import { TestBed } from '@angular/core/testing';

import { CulturalValidatorService } from './cultural-validator.service';
import { CulturalService } from './cultural.service';

describe('CulturalValidatorService', () => {
  let service: CulturalValidatorService;

  beforeEach(() => {
    const culturalServiceMock = {};
    TestBed.configureTestingModule({
      providers: [
        {provide: CulturalService, useValue: culturalServiceMock}
      ]
    });
    service = TestBed.inject(CulturalValidatorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
