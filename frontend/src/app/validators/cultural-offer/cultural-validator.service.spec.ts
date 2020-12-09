import { TestBed } from '@angular/core/testing';

import { CulturalService } from 'src/app/services/cultural-offer/cultural.service';
import { CulturalValidatorService } from './cultural-validator.service';

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
