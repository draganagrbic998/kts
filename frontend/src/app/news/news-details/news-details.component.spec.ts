import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { SharedModule } from 'src/app/shared/shared.module';
import { NewsService } from 'src/app/news/services/news.service';
import { NewsDetailsComponent } from './news-details.component';

describe('NewsDetailsComponent', () => {
  let component: NewsDetailsComponent;
  let fixture: ComponentFixture<NewsDetailsComponent>;

  beforeEach(async () => {
    const newsServiceMock = {};
    await TestBed.configureTestingModule({
      declarations: [ NewsDetailsComponent ],
      imports: [
        SharedModule
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
      providers: [
        {provide: NewsService, useValue: newsServiceMock}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewsDetailsComponent);
    component = fixture.componentInstance;
    component.news = {
      id: null,
      createdAt: null,
      text: null,
      images: [],
      culturalOfferId: null
    };
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
