import { Component, OnInit } from '@angular/core';
import { Category } from '../utils/category';

@Component({
  selector: 'app-categories-dialog',
  templateUrl: './categories-dialog.component.html',
  styleUrls: ['./categories-dialog.component.sass']
})
export class CategoriesDialogComponent implements OnInit {

  constructor() { }
  selectedTag: number = 0;
  fetchPending: boolean = true;
  categories: Category[] = [undefined];
  title: string[] = [
    "Add Category", 
    "All Categories"
  ];
  ngOnInit(): void {
    this.fetchData();
  }

  changeTab(index: number): void{
    this.selectedTag = index;
  }
  fetchData() : void{
      //dobavi sve kategorije sa servera
  }

}
