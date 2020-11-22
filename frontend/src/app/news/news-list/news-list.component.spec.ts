import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LayoutModule } from 'src/app/layout/layout.module';
import { NewsService } from '../services/news.service';

import { NewsListComponent } from './news-list.component';

describe('NewsListComponent', () => {
  let component: NewsListComponent;
  let fixture: ComponentFixture<NewsListComponent>;

  beforeEach(async () => {
    const newsServiceMock = {};
    await TestBed.configureTestingModule({
      declarations: [ NewsListComponent ],
      imports: [
        LayoutModule,
      ],
      providers: [
        {provide: NewsService, useValue: newsServiceMock}
      ], 
      schemas: [CUSTOM_ELEMENTS_SCHEMA]
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
