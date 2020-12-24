import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { SNACKBAR_ERROR_OPTIONS, SNACKBAR_CLOSE, SNACKBAR_SUCCESS_OPTIONS } from 'src/app/constants/snackbar';

@Component({
  selector: 'app-delete-confirmation',
  templateUrl: './delete-confirmation.component.html',
  styleUrls: ['./delete-confirmation.component.sass']
})
export class DeleteConfirmationComponent implements OnInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) private deleteFunction: () => Observable<null | number>,
    public dialogRef: MatDialogRef<DeleteConfirmationComponent>,
    private snackBar: MatSnackBar
  ) { }

  deletePending = false;

  confirm(): void{
    this.deletePending = true;
    this.deleteFunction().subscribe(
      (param: null | number) => {
        this.dialogRef.close(typeof param === 'number' ? param : true);
        this.snackBar.open('Item successfully removed!', SNACKBAR_CLOSE, SNACKBAR_SUCCESS_OPTIONS);
      },
      () => {
        this.deletePending = false;
        this.snackBar.open('Item cannot be removed!', SNACKBAR_CLOSE, SNACKBAR_ERROR_OPTIONS);
      }
    );
  }

  ngOnInit(): void {
  }

}
