import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoldTextComponent } from './bold-text.component';

describe('BoldTextComponent', () => {
  let component: BoldTextComponent;
  let fixture: ComponentFixture<BoldTextComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoldTextComponent ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoldTextComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
