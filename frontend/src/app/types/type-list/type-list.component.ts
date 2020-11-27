import { Component, OnInit } from '@angular/core';
import { TypeService } from '../services/type.service';
import { Type } from '../utils/type';

@Component({
  selector: 'app-type-list',
  templateUrl: './type-list.component.html',
  styleUrls: ['./type-list.component.sass']
})
export class TypeListComponent implements OnInit {

  constructor(
    private typeService: TypeService
  ) { }
  
  types: Type[];
  fetchPending: boolean = true;

  ngOnInit(): void {
    this.typeService.list().subscribe(
      (types:Type[]) => {
        this.types = types;
        this.fetchPending = false;
      }, 
      () => {
        this.fetchPending = false;
      }
    );
  }

}
