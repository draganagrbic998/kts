import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CulturalListComponent } from './cultural-list.component';

describe('CulturalListComponent', () => {
  let component: CulturalListComponent;
  let fixture: ComponentFixture<CulturalListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CulturalListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CulturalListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
