import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
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
  

}
