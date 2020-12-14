import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { of } from 'rxjs';
import { LayoutModule } from 'src/app/layout/layout.module';
import { CategoryService } from 'src/app/services/category/category.service';
import { TypeService } from 'src/app/services/type/type.service';
import { TypeValidatorService } from 'src/app/validators/type/type-validator.service';
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
