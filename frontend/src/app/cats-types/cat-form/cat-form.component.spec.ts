import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LayoutModule } from 'src/app/layout/layout.module';
import { CategoryService } from 'src/app/services/category/category.service';
import { CatFormComponent } from './cat-form.component';

describe('CatFormComponent', () => {
  let component: CatFormComponent;
  let fixture: ComponentFixture<CatFormComponent>;

  beforeEach(async () => {
    const categoryServiceMock = {};
    await TestBed.configureTestingModule({
      declarations: [ CatFormComponent ],
      imports: [
        LayoutModule,
        BrowserAnimationsModule
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
      providers: [
        {provide: CategoryService, useValue: categoryServiceMock}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CatFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
