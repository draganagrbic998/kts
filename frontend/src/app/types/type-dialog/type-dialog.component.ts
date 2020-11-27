import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-type-dialog',
  templateUrl: './type-dialog.component.html',
  styleUrls: ['./type-dialog.component.sass']
})
export class TypeDialogComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<TypeDialogComponent>
  ) { }

  ngOnInit(): void {
  }

}
