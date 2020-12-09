import { Component, EventEmitter, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { ERROR_SNACKBAR_OPTIONS, SNACKBAR_CLOSE, SUCCESS_SNACKBAR_OPTIONS } from 'src/app/constants/dialog';

@Component({
  selector: 'app-delete-confirmation',
  templateUrl: './delete-confirmation.component.html',
  styleUrls: ['./delete-confirmation.component.sass']
})
export class DeleteConfirmationComponent implements OnInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) private deleteFunction: () => Observable<null>,
    public dialogRef: MatDialogRef<DeleteConfirmationComponent>,
    private snackBar: MatSnackBar
  ) { }

  deletePending = false;
  deleted: EventEmitter<any> = new EventEmitter();

  confirm(): void{
    this.deletePending = true;
    this.deleteFunction().subscribe(
      (response) => {
        this.dialogRef.close();
        this.deleted.emit(response);
        this.snackBar.open('Item successfully removed!', SNACKBAR_CLOSE, SUCCESS_SNACKBAR_OPTIONS);
      },
      () => {
        this.deletePending = false;
        this.snackBar.open('Item cannot be removed!', SNACKBAR_CLOSE, ERROR_SNACKBAR_OPTIONS);
      }
    );
  }

  ngOnInit(): void {
  }

}
