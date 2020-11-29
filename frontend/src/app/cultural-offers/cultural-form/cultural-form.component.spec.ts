import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CulturalFormComponent } from './cultural-form.component';

describe('CulturalFormComponent', () => {
  let component: CulturalFormComponent;
  let fixture: ComponentFixture<CulturalFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CulturalFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CulturalFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
