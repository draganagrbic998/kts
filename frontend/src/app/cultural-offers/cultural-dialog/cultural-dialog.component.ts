import { Component, EventEmitter, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CulturalOffer } from '../utils/cultural-offer';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserFollowingService } from '../services/user-following.service';
import { DIALOG_OPTIONS, ERROR_MESSAGE, ERROR_SNACKBAR_OPTIONS, SNACKBAR_CLOSE } from 'src/app/utils/constants';
import { AuthService } from 'src/app/services/auth.service';
import { CulturalService } from '../services/cultural.service';
import { DeleteConfirmationComponent } from 'src/app/layout/delete-confirmation/delete-confirmation.component';
import { Router } from '@angular/router';
import { LOGIN_PATH } from 'src/app/user/utils/router';
import { USER_PATH } from 'src/app/utils/router';

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
    private router: Router,
    private dialog: MatDialog,
    public dialogRef:MatDialogRef<CulturalDialogComponent>, 
    private snackBar: MatSnackBar
  ) { }

  toggleSubPending: boolean = false;
  onRefreshData: EventEmitter<CulturalOffer | number> = new EventEmitter();

  get role(): string{
    return this.authService.getUser()?.role;
  }

  edit(): void{
    
  }

  delete(): void{
    const options = {...DIALOG_OPTIONS, ...{data: () => this.culturalService.delete(this.culturalOffer.id)}};
    const dialog: MatDialogRef<DeleteConfirmationComponent> = this.dialog.open(DeleteConfirmationComponent, options);
    dialog.componentInstance.onDeleted.subscribe(
      () => {
        this.dialogRef.close();
        this.onRefreshData.emit(this.culturalOffer.id);
      }
    );  
  }

  toggleSubscription(): void{
    if (!this.authService.getUser()){
      this.dialogRef.close();
      this.router.navigate([`${USER_PATH}/${LOGIN_PATH}`]);
      return;
    }
    
    this.toggleSubPending = true;
    this.userFollowingService.toggleSubscription(this.culturalOffer.id).subscribe(
      () => {
        this.toggleSubPending = false;
        this.culturalOffer.followed = !this.culturalOffer.followed;
        this.onRefreshData.emit(this.culturalOffer.followed ? this.culturalOffer : this.culturalOffer.id);
      }, 
      () => {
        this.toggleSubPending = false;
        this.snackBar.open(ERROR_MESSAGE, SNACKBAR_CLOSE, ERROR_SNACKBAR_OPTIONS);
      }
    );
  }

  ngOnInit(): void {
  }

}
