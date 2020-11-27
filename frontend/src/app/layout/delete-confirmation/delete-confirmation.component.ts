import { Component, EventEmitter, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { ERROR_MESSAGE, ERROR_SNACKBAR_OPTIONS, SNACKBAR_CLOSE, SUCCESS_SNACKBAR_OPTIONS } from 'src/app/utils/constants';

@Component({
  selector: 'app-delete-confirmation',
  templateUrl: './delete-confirmation.component.html',
  styleUrls: ['./delete-confirmation.component.sass']
})
export class DeleteConfirmationComponent {
  
  constructor(
    @Inject(MAT_DIALOG_DATA) private deleteFunction: () => Observable<null>,
    public dialogRef: MatDialogRef<DeleteConfirmationComponent>, 
    private snackBar: MatSnackBar
  ) { }

  deletePending: boolean = false;
  onDeleted: EventEmitter<null> = new EventEmitter();

  confirm(): void{
    this.deletePending = true;
    this.deleteFunction().subscribe(
      () => {
        this.dialogRef.close();
        this.snackBar.open("Item has been successfully removed!", SNACKBAR_CLOSE, SUCCESS_SNACKBAR_OPTIONS);
        this.onDeleted.emit();
      }, 
      () => {
        this.deletePending = false;
        this.snackBar.open(ERROR_MESSAGE, SNACKBAR_CLOSE, ERROR_SNACKBAR_OPTIONS);
      }
    )
  }

  ngOnInit(): void {
  }
  
}
