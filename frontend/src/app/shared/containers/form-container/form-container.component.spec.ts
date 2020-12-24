import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormContainerComponent } from './form-container.component';

describe('FormContainerComponent', () => {
  let component: FormContainerComponent;
  let fixture: ComponentFixture<FormContainerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormContainerComponent ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
