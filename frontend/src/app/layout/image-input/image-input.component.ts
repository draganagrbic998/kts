import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-image-input',
  templateUrl: './image-input.component.html',
  styleUrls: ['./image-input.component.sass']
})
export class ImageInputComponent implements OnInit {

  constructor() { }

  @Input() image: string;
  @Input() smallImage: boolean;
  @Input() profile: boolean;
  @Output() onAddImage: EventEmitter<any> = new EventEmitter();
  @Output() onRemoveImage: EventEmitter<null> = new EventEmitter();

  ngOnInit(): void {
  }

}
