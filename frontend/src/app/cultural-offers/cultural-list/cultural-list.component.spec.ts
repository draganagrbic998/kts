import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormControl, FormGroup } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LayoutModule } from 'src/app/layout/layout.module';
import { CulturalService } from 'src/app/services/cultural-offer/cultural.service';
import { CulturalListComponent } from './cultural-list.component';

describe('CulturalListComponent', () => {
  let component: CulturalListComponent;
  let fixture: ComponentFixture<CulturalListComponent>;

  beforeEach(async () => {
    const culturalServiceMock = {};
    await TestBed.configureTestingModule({
      declarations: [ CulturalListComponent ],
      imports: [
        LayoutModule,
        BrowserAnimationsModule
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
      providers: [
        {provide: CulturalService, useValue: culturalServiceMock},
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CulturalListComponent);
    component = fixture.componentInstance;
    component.filterForm = new FormGroup({
      name: new FormControl(''),
      location: new FormControl(''),
      type: new FormControl('')
    });
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
