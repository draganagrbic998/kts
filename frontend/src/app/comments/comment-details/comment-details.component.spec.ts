import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CommentDetailsComponent } from './comment-details.component';
import { CommentService } from 'src/app/comments/services/comment.service';
import { MatDialog } from '@angular/material/dialog';
import { AuthService } from 'src/app/shared/services/auth/auth.service';
import { User } from 'src/app/models/user';

describe('CommentDetailsComponent', () => {
  let component: CommentDetailsComponent;
  let fixture: ComponentFixture<CommentDetailsComponent>;
  const testUser: User = {
    id: 1,
    accessToken: 'some token',
    role: 'some role',
    email: 'some email',
    firstName: 'some first name',
    lastName: 'some last name',
    image: 'some image'
  };

  beforeEach(async () => {
    const authServiceMock = {
      getUser: jasmine.createSpy('getUser').and.returnValue(testUser)
    };
    const commentServiceMock = {};
    const dialogMock = {};
    await TestBed.configureTestingModule({
      declarations: [ CommentDetailsComponent ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
      providers: [
        {provide: AuthService, useValue: authServiceMock},
        {provide: CommentService, useValue: commentServiceMock},
        {provide: MatDialog, useValue: dialogMock}
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
