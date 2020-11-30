import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LayoutModule } from 'src/app/layout/layout.module';
import { CommentService } from '../services/comment.service';

import { CommentDetailsComponent } from './comment-details.component';

describe('CommentDetailsComponent', () => {
  let component: CommentDetailsComponent;
  let fixture: ComponentFixture<CommentDetailsComponent>;

  beforeEach(async () => {
    const commentServiceMock = {};
    await TestBed.configureTestingModule({
      declarations: [ CommentDetailsComponent ], 
      imports: [
        LayoutModule
      ], 
      schemas: [CUSTOM_ELEMENTS_SCHEMA], 
      providers: [
        {provide: CommentService, useValue: commentServiceMock}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CommentDetailsComponent);
    component = fixture.componentInstance;
    component.comment = {
      id: null, 
      createdAt: null, 
      rate: null,
      text: null, 
      images: [], 
      user: null
    };
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
