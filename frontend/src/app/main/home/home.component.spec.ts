import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { of } from 'rxjs';
import { CulturalService } from 'src/app/cultural-offers/services/cultural.service';
import { LayoutModule } from 'src/app/layout/layout.module';
import { AuthService } from 'src/app/utils/services/auth.service';

import { HomeComponent } from './home.component';

describe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;

  beforeEach(async () => {
    const authServiceMock = {
      getUser: jasmine.createSpy('getUser').and.returnValue(null)
    };
    const culturalServiceMock = {
      filter: jasmine.createSpy('filter').and.returnValue(of(null))
    };
    await TestBed.configureTestingModule({
      declarations: [ HomeComponent ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA], 
      imports: [
        LayoutModule, 
        BrowserAnimationsModule
      ], 
      providers: [
        {provide: AuthService, useValue: authServiceMock},
        {provide: CulturalService, useValue: culturalServiceMock}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
