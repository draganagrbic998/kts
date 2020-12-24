import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { CommentDetailsComponent } from './comment-details.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { CommentService } from 'src/app/comments/services/comment.service';

describe('CommentDetailsComponent', () => {
  let component: CommentDetailsComponent;
  let fixture: ComponentFixture<CommentDetailsComponent>;

  beforeEach(async () => {
    const commentServiceMock = {};
    await TestBed.configureTestingModule({
      declarations: [ CommentDetailsComponent ],
      imports: [
        SharedModule,
        HttpClientTestingModule
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
      user: null,
      culturalOfferId: null
    };
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
