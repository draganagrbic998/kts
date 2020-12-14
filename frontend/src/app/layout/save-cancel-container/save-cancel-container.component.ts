import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-save-cancel-container',
  templateUrl: './save-cancel-container.component.html',
  styleUrls: ['./save-cancel-container.component.sass']
})
export class SaveCancelContainerComponent implements OnInit {

  constructor() { }

  @Input() pending: boolean;
  @Input() inputControl: FormGroup | FormControl;
  @Output() cancelled: EventEmitter<null> = new EventEmitter();
  @Output() saved: EventEmitter<null> = new EventEmitter();

  ngOnInit(): void {
  }

}
