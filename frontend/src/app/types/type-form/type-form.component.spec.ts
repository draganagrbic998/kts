import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { of } from 'rxjs';
import { CategoryService } from 'src/app/categories/services/category.service';
import { LayoutModule } from 'src/app/layout/layout.module';
import { TypeValidatorService } from '../services/type-validator.service';
import { TypeService } from '../services/type.service';

import { TypeFormComponent } from './type-form.component';

describe('TypeFormComponent', () => {
  let component: TypeFormComponent;
  let fixture: ComponentFixture<TypeFormComponent>;

  beforeEach(async () => {
    const typeServiceMock = {};
    const categoryServiceMock = {
      filterNames: jasmine.createSpy('filterNames').and.returnValue(of(null)),
    };
    const typeValidatorMock = {
      hasName: jasmine.createSpy('hasName').and.returnValue(() => of(null)),
    };
    await TestBed.configureTestingModule({
      declarations: [ TypeFormComponent ],
      imports: [
        LayoutModule,
        BrowserAnimationsModule
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
      providers: [
        {provide: TypeService, useValue: typeServiceMock},
        {provide: CategoryService, useValue: categoryServiceMock},
        {provide: TypeValidatorService, useValue: typeValidatorMock}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TypeFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
