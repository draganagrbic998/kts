import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PreloaderComponent } from './preloader.component';

describe('PreloaderComponent', () => {
  let component: PreloaderComponent;
  let fixture: ComponentFixture<PreloaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PreloaderComponent ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PreloaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
