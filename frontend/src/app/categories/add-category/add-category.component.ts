import { Component, OnInit,Input, Output, EventEmitter } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.sass']
})
export class AddCategoryComponent implements OnInit {

  constructor() { }
  @Input() title: string;
  @Output() onRefreshData: EventEmitter<string> = new EventEmitter();

  addPending: boolean = false;
  addForm: FormGroup = new FormGroup({
    name: new FormControl("", [Validators.required, Validators.pattern(new RegExp("\\S"))])
  });
  ngOnInit(): void {
  }

  refreshData(): void{
    this.onRefreshData.emit("dodata nova kategorija");
  }

}
