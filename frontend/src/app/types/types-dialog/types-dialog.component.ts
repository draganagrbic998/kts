import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ERROR_SNACKBAR_OPTIONS } from 'src/app/utils/constants';
import { TypeService } from '../services/type.service';
import { Type } from '../utils/Type';

@Component({
  selector: 'app-types-dialog',
  templateUrl: './types-dialog.component.html',
  styleUrls: ['./types-dialog.component.sass']
})
export class TypesDialogComponent implements OnInit {

  constructor(private snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<TypesDialogComponent>,
    private typeService: TypeService
) { }
  title: String[] = ["Add Type","All Types"];
  selectedTag: number = 0;
  types: Type[] = [];
  fetchPending: boolean = true;
  ngOnInit(): void {
    this.fetchData();
  }
  
  changeTab(index: number): void{
    this.selectedTag = index;
  }
  fetchData() : void{
    //dobavi sve kategorije sa servera
    this.typeService.getAllTypes().subscribe(
      (types:Type[]) => {
        this.types = types;
        this.fetchPending = false;
      }, 
      () => {
        this.fetchPending = false;
        this.snackBar.open("An error occured! Try again.", 
        "Close", ERROR_SNACKBAR_OPTIONS);
      }
    )
  }
}
