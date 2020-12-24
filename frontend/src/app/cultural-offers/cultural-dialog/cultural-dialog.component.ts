import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CommentFormComponent } from 'src/app/comments/comment-form/comment-form.component';
import { CulturalOffer } from 'src/app/models/cultural-offer';
import { NewsFormComponent } from 'src/app/news/news-form/news-form.component';
import { CulturalService } from 'src/app/cultural-offers/services/cultural.service';
import { RateUpdate } from 'src/app/models/rate-update';
import { UserFollowingService } from 'src/app/cultural-offers/services/user-following.service';
import { CulturalFormComponent } from '../cultural-form/cultural-form.component';
import { AuthService } from 'src/app/shared/services/auth/auth.service';
import { DIALOG_OPTIONS } from 'src/app/constants/dialog';
import { DeleteConfirmationComponent } from 'src/app/shared/controls/delete-confirmation/delete-confirmation.component';
import { SNACKBAR_CLOSE, SNACKBAR_ERROR_MESSAGE, SNACKBAR_ERROR_OPTIONS } from 'src/app/constants/snackbar';

@Component({
  selector: 'app-cultural-dialog',
  templateUrl: './cultural-dialog.component.html',
  styleUrls: ['./cultural-dialog.component.sass']
})
export class CulturalDialogComponent implements OnInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) public culturalOffer: CulturalOffer,
    private authService: AuthService,
    private culturalService: CulturalService,
    private userFollowingService: UserFollowingService,
    private dialog: MatDialog,
    public dialogRef: MatDialogRef<CulturalDialogComponent>,
    private snackBar: MatSnackBar
  ) { }

  toggleSubPending = false;

  get role(): string{
    return this.authService.getUser()?.role;
  }

  edit(): void{
    const options = {...DIALOG_OPTIONS, ...{data: this.culturalOffer}};
    this.dialog.open(CulturalFormComponent, options).afterClosed().subscribe(result => {
      if (result){
        this.dialogRef.close();
      }
    });
  }

  delete(): void{
    const options = {...DIALOG_OPTIONS, ...{data: () => this.culturalService.delete(this.culturalOffer.id)}};
    this.dialog.open(DeleteConfirmationComponent, options).afterClosed().subscribe(result => {
      if (result){
        this.culturalService.announceRefreshData(this.culturalOffer.id);
        this.dialogRef.close();
      }
    });
  }

  toggleSubscription(): void{
    this.toggleSubPending = true;
    this.userFollowingService.toggleSubscription(this.culturalOffer.id).subscribe(
      () => {
        this.toggleSubPending = false;
        this.culturalOffer.followed = !this.culturalOffer.followed;
        this.culturalService.announceRefreshData(this.culturalOffer);
      },
      () => {
        this.toggleSubPending = false;
        this.snackBar.open(SNACKBAR_ERROR_MESSAGE, SNACKBAR_CLOSE, SNACKBAR_ERROR_OPTIONS);
      }
    );
  }

  publishReview(): void{
    const options = {...DIALOG_OPTIONS, ...{data: {culturalOfferId: this.culturalOffer.id, images: []}}};
    this.dialog.open(CommentFormComponent, options);
  }

  publishNews(): void{
    const options = {...DIALOG_OPTIONS, ...{data: {culturalOfferId: this.culturalOffer.id, images: []}}};
    this.dialog.open(NewsFormComponent, options);
  }

  ngOnInit(): void {
    this.culturalService.updateTotalRate$.subscribe((param: RateUpdate) => {
      if (param.id === this.culturalOffer.id){
        this.culturalOffer.totalRate = param.totalRate;
      }
    });
  }

}
