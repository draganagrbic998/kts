import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NewsListComponent } from './news-list.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { NewsService } from 'src/app/news/services/news.service';

describe('NewsListComponent', () => {
  let component: NewsListComponent;
  let fixture: ComponentFixture<NewsListComponent>;

  beforeEach(async () => {
    const newsServiceMock = {
      filter: jasmine.createSpy('filter').and.returnValue(of(null))
    };
    await TestBed.configureTestingModule({
      declarations: [ NewsListComponent ],
      imports: [
        SharedModule,
        BrowserAnimationsModule
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
      providers: [
        {provide: NewsService, useValue: newsServiceMock}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
