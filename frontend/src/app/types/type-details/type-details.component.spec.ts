import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LayoutModule } from 'src/app/layout/layout.module';
import { TypeService } from '../services/type.service';

import { TypeDetailsComponent } from './type-details.component';

describe('TypeDetailsComponent', () => {
  let component: TypeDetailsComponent;
  let fixture: ComponentFixture<TypeDetailsComponent>;

  beforeEach(async () => {
    const typeServiceMock = {};
    await TestBed.configureTestingModule({
      declarations: [ TypeDetailsComponent ],
      imports: [
        LayoutModule
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA], 
      providers: [
        {provide: TypeService, useValue: typeServiceMock}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TypeDetailsComponent);
    component = fixture.componentInstance;
    component.type = {
      id: null, 
      name: null,
      category: null, 
      placemarkIcon: null
    }
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
