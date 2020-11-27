import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { of } from 'rxjs';
import { CulturalService } from 'src/app/cultural-offers/services/cultural.service';
import { UserFollowingService } from 'src/app/cultural-offers/services/user-following.service';
import { LayoutModule } from 'src/app/layout/layout.module';

import { HomeComponent } from './home.component';

describe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;

  beforeEach(async () => {
    const culturalServiceMock = {
      filter: jasmine.createSpy('filter').and.returnValue(of(null))
    };
    const userFollowingServiceMock = {
      filter: jasmine.createSpy('filter').and.returnValue(of(null))
    };
    await TestBed.configureTestingModule({
      declarations: [ HomeComponent ],
      imports: [
        LayoutModule, 
        BrowserAnimationsModule
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA], 
      providers: [
        {provide: CulturalService, useValue: culturalServiceMock}, 
        {provide: UserFollowingService, useValue: userFollowingServiceMock}
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
