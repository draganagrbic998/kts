import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CulturalDetailsComponent } from './cultural-details.component';

describe('CulturalDetailsComponent', () => {
  let component: CulturalDetailsComponent;
  let fixture: ComponentFixture<CulturalDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CulturalDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CulturalDetailsComponent);
    component = fixture.componentInstance;
    component.culturalOffer = {
      id: null,
      category: '',
      type: '',
      placemarkIcon: '',
      name: '',
      description: '',
      image: '',
      location: '',
      lat: null,
      lng: null,
      totalRate: 0,
      followed: false
    };
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
