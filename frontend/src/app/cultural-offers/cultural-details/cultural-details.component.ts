import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { CulturalOffer } from 'src/app/models/cultural-offer';

@Component({
  selector: 'app-cultural-details',
  templateUrl: './cultural-details.component.html',
  styleUrls: ['./cultural-details.component.sass']
})
export class CulturalDetailsComponent implements OnInit {

  constructor() { }

  @Input() culturalOffer: CulturalOffer;
  @Output() markOnMap: EventEmitter<CulturalOffer> = new EventEmitter();

  ngOnInit(): void {
  }

}
