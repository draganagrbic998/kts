import { Component, EventEmitter, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AuthService } from 'src/app/utils/services/auth.service';
import { CulturalOffer } from '../utils/cultural-offer';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserFollowingService } from '../services/user-following.service';
import { HttpResponse } from '@angular/common/http';
import { ERROR_SNACKBAR_OPTIONS, SUCCESS_SNACKBAR_OPTIONS } from 'src/app/utils/constants';


@Component({
  selector: 'app-cultural-dialog',
  templateUrl: './cultural-dialog.component.html',
  styleUrls: ['./cultural-dialog.component.sass']
})
export class CulturalDialogComponent implements OnInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) public culturalOffer: CulturalOffer, 
    private authService: AuthService,
    private userFollowingService: UserFollowingService,
    public dialogRef: MatDialogRef<CulturalDialogComponent>,
    private snackBar: MatSnackBar,
  ) { }

  toggleSubPending: boolean = false;
  onToggleSubscription: EventEmitter<CulturalOffer | number> = new EventEmitter();

  get role(): string{
    return this.authService.getUser()?.role;
  }

  get isSubscribedTo(): boolean{
    return this.culturalOffer?.followed;
  }

  toggleSubscription(): void{
    this.toggleSubPending = true;
    
    this.userFollowingService.toggleSubscription(this.culturalOffer.id).subscribe(
      () => {
        this.toggleSubPending = false;

        if(!this.culturalOffer.followed) {
          this.culturalOffer.followed = true;
          this.onToggleSubscription.emit(this.culturalOffer);
        }
        else {
          this.culturalOffer.followed = false;
          this.onToggleSubscription.emit(this.culturalOffer.id);
        }

        if (this.culturalOffer)
          this.snackBar.open("You have successfully subscribed!", "Close", SUCCESS_SNACKBAR_OPTIONS);
        else
          this.snackBar.open("You have successfully unsubscribed!", "Close", SUCCESS_SNACKBAR_OPTIONS);
      }, 
      () => {
        this.toggleSubPending = false;
        this.snackBar.open("An error occured! Try again.", 
        "Close", ERROR_SNACKBAR_OPTIONS);
      }
    )
  }

  edit(): void{

  }

  delete(): void{
    
  }
  
  ngOnInit(): void {
  }


}
