import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SharedModule } from 'src/app/shared/shared.module';
import { CulturalService } from 'src/app/cultural-offers/services/cultural.service';
import { CulturalListComponent } from './cultural-list.component';

describe('CulturalListComponent', () => {
  let component: CulturalListComponent;
  let fixture: ComponentFixture<CulturalListComponent>;

  beforeEach(async () => {
    const culturalServiceMock = {};
    await TestBed.configureTestingModule({
      declarations: [ CulturalListComponent ],
      imports: [
        SharedModule,
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
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
