import { NgModule } from '@angular/core';
import { LayoutModule } from '../layout/layout.module';
import { CommentDetailsComponent } from './comment-details/comment-details.component';
import { StarRatingComponent } from './star-rating/star-rating.component';



@NgModule({
  declarations: [
    CommentDetailsComponent, 
    StarRatingComponent
  ],
  imports: [
    LayoutModule
  ]
})
export class CommentsModule { }
