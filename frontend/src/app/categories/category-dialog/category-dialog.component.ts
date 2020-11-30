import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-category-dialog',
  templateUrl: './category-dialog.component.html',
  styleUrls: ['./category-dialog.component.sass']
})
export class CategoryDialogComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<CategoryDialogComponent>
  ) { }

  ngOnInit(): void {
  }

}
