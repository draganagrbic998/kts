import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CulturalDialogComponent } from './cultural-dialog.component';

describe('CulturalDialogComponent', () => {
  let component: CulturalDialogComponent;
  let fixture: ComponentFixture<CulturalDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CulturalDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CulturalDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
