import { TestBed } from '@angular/core/testing';

import { CulturalService } from './cultural.service';

describe('CulturalService', () => {
  let service: CulturalService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CulturalService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
