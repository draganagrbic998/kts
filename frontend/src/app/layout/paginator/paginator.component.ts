import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-paginator',
  templateUrl: './paginator.component.html',
  styleUrls: ['./paginator.component.sass']
})
export class PaginatorComponent implements OnInit {

  constructor() { }

  @Input() fixed: boolean;
  @Input() fetchPending: boolean;
  @Input() hasPrevious: boolean;
  @Input() hasNext: boolean;
  @Input() title: string;
  @Output() changedPage: EventEmitter<number> = new EventEmitter();

  ngOnInit(): void {
  }

}
