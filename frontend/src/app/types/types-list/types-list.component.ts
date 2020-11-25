import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TypeService } from '../services/type.service';
import { Type } from '../utils/Type';

@Component({
  selector: 'app-types-list',
  templateUrl: './types-list.component.html',
  styleUrls: ['./types-list.component.sass']
})
export class TypesListComponent implements OnInit {

  constructor(
    private typeService: TypeService,
    private snackBar: MatSnackBar) { }
  @Input() types: Type[];
  @Input() fetchPending: boolean;
  @Output() onRefreshData: EventEmitter<string> = new EventEmitter();

  ngOnInit(): void {
  }

  refreshData(): void{
    this.onRefreshData.emit("obrisan tip");
  }

}
