import { TestBed } from '@angular/core/testing';

import { CategoryValidatorService } from './category-validator.service';
import { CategoryService } from './category.service';

describe('CategoryValidatorService', () => {
  let service: CategoryValidatorService;

  beforeEach(() => {
    const categoryServiceMock = {};
    TestBed.configureTestingModule({
      providers: [
        {provide: CategoryService, useValue: categoryServiceMock}
      ]
    });
    service = TestBed.inject(CategoryValidatorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
