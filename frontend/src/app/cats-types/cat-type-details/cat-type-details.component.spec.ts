import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LayoutModule } from 'src/app/layout/layout.module';
import { CategoryService } from 'src/app/services/category/category.service';
import { CatTypeDetailsComponent } from './cat-type-details.component';

describe('CatTypeDetailsComponent', () => {
  let component: CatTypeDetailsComponent;
  let fixture: ComponentFixture<CatTypeDetailsComponent>;

  beforeEach(async () => {
    const categoryServiceMock = {};
    await TestBed.configureTestingModule({
      declarations: [ CatTypeDetailsComponent ],
      imports: [
        LayoutModule
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
      providers: [
        {provide: CategoryService, useValue: categoryServiceMock}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CatTypeDetailsComponent);
    component = fixture.componentInstance;
    component.catType = {
      id: null,
      name: null
    };
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
