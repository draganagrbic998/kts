import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmationDialogComponent } from 'src/app/layout/confirmation-dialog/confirmation-dialog.component';
import { ERROR_SNACKBAR_OPTIONS, SUCCESS_SNACKBAR_OPTIONS } from 'src/app/utils/constants';
import { TypeService } from '../services/type.service';
import { Type } from '../utils/Type';

@Component({
  selector: 'app-type-details',
  templateUrl: './type-details.component.html',
  styleUrls: ['./type-details.component.sass']
})
export class TypeDetailsComponent implements OnInit {

  constructor(private typeService: TypeService,
    private snackBar: MatSnackBar,
    public dialog: MatDialog) { }

  @Input() currentType: Type;
  @Output() onRefreshData: EventEmitter<string> = new EventEmitter();
  delPending: boolean = false;

  ngOnInit(): void {
  }
  
  delete(): void {
    const dialog: MatDialogRef<ConfirmationDialogComponent> = this.dialog.open(ConfirmationDialogComponent, 
      {disableClose: true, panelClass: "no-padding", data: "Delete type?"});

      dialog.afterClosed().subscribe((result) => {
        if (result) {
          this.delPending = true;
    
          this.typeService.delete(this.currentType.id).subscribe(
            () => {
              this.delPending = false;

              this.onRefreshData.emit("obrisan tip");
              console.log("obrisana tip");
        
              this.snackBar.open("You have successfully deleted this type!", "Close", SUCCESS_SNACKBAR_OPTIONS);
            }, 
            () => {
              this.delPending = false;
              this.snackBar.open("Can't delete this type because there are cultural offers that have this type!", 
              "Close", ERROR_SNACKBAR_OPTIONS);
            }
          )
        }
      });
    }

}
