import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../services/category.service';
import { Category } from '../utils/category';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.sass']
})
export class CategoryListComponent implements OnInit {

  constructor(
    private categoryService: CategoryService
  ) { }

  categories: Category[];
  fetchPending: boolean = true;

  ngOnInit(): void {
    this.categoryService.list().subscribe(
      (categories: Category[]) => {
        this.categories = categories;
        this.fetchPending = false;
      }, 
      () => {
        this.fetchPending = false;
      }
    );
  }

}
