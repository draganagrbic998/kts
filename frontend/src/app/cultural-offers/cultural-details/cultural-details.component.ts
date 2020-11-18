import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { CulturalOffer } from '../utils/cultural-offer';

@Component({
  selector: 'app-cultural-details',
  templateUrl: './cultural-details.component.html',
  styleUrls: ['./cultural-details.component.sass']
})
export class CulturalDetailsComponent implements OnInit {

  constructor() { }

  @Input() culturalOffer: CulturalOffer;
  @Output() onMarkOnMap: EventEmitter<CulturalOffer> = new EventEmitter();
  transparent: boolean = false;

  ngOnInit(): void {
  }

}
