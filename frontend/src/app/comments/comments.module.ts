import { NgModule } from '@angular/core';
import { LayoutModule } from '../layout/layout.module';
import { CommentDetailsComponent } from './comment-details/comment-details.component';
import { StarRatingComponent } from './star-rating/star-rating.component';
import { CommentListComponent } from './comment-list/comment-list.component';
import { CommentFormComponent } from './comment-form/comment-form.component';

@NgModule({
  declarations: [
    CommentDetailsComponent, 
    CommentListComponent,
    CommentFormComponent,
    StarRatingComponent
  ],
  imports: [
    LayoutModule
  ], 
  exports: [
    CommentListComponent, 
    CommentFormComponent
  ]
})
export class CommentsModule { }
