import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { LayoutModule } from 'src/app/layout/layout.module';
import { CategoryService } from 'src/app/services/category/category.service';
import { CatTypeListComponent } from './cat-type-list.component';

describe('CatTypeListComponent', () => {
  let component: CatTypeListComponent;
  let fixture: ComponentFixture<CatTypeListComponent>;

  beforeEach(async () => {
    const categoryServiceMock = {
      list: jasmine.createSpy('list').and.returnValue(of(null))
    };
    await TestBed.configureTestingModule({
      declarations: [ CatTypeListComponent ],
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
    fixture = TestBed.createComponent(CatTypeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
