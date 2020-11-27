import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-star-rating',
  templateUrl: './star-rating.component.html',
  styleUrls: ['./star-rating.component.sass']
})
export class StarRatingComponent implements OnInit {

  constructor() {}
  
  @Input() starCount: number = 5;
  @Input() rate: number = 0;
  @Input() editable: boolean = true;
  @Output() onRated: EventEmitter<number> = new EventEmitter();
  rateArray: number[] = [];

  starClick(index: number): void {
    if (index !== this.rate){
      this.rate = index;
    }
    else{
      --this.rate;
    }
    this.onRated.emit(this.rate);
  }

  starIcon(index: number): string {
    if (!this.editable){
      return 'star';
    }
    if (this.rate >= index + 1) {
      return 'star';
    };
    return 'star_border';
  }

  ngOnInit(): void {
    for (let index: number = 0; index < this.starCount; ++index) {
      this.rateArray.push(index);
    }
  }

}
