import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { DeleteConfirmationComponent } from 'src/app/layout/delete-confirmation/delete-confirmation.component';
import { DIALOG_OPTIONS } from 'src/app/utils/constants';
import { TypeService } from '../services/type.service';
import { Type } from '../utils/type';

@Component({
  selector: 'app-type-details',
  templateUrl: './type-details.component.html',
  styleUrls: ['./type-details.component.sass']
})
export class TypeDetailsComponent implements OnInit {

  constructor(
    private typeService: TypeService,
    private dialog: MatDialog 
  ) { }

  @Input() type: Type;
  @Output() onDeleted: EventEmitter<null> = new EventEmitter();
  
  delete(): void {
    const options = {...DIALOG_OPTIONS, ...{data: () => this.typeService.delete(this.type.id)}}
    const dialog: MatDialogRef<DeleteConfirmationComponent> = this.dialog.open(DeleteConfirmationComponent, options);
    dialog.componentInstance.onDeleted.subscribe(
      () => {
        this.onDeleted.emit();
      }
    );
  }
  
  ngOnInit(): void {
  }

}
