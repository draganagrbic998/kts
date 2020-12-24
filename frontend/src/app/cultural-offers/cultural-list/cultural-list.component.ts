import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { CulturalOffer } from 'src/app/models/cultural-offer';
import { Pagination } from 'src/app/models/pagination';

@Component({
  selector: 'app-cultural-list',
  templateUrl: './cultural-list.component.html',
  styleUrls: ['./cultural-list.component.sass']
})
export class CulturalListComponent implements OnInit {

  constructor() { }

  @Input() title: string;
  @Input() culturalOffers: CulturalOffer[];
  @Input() fetchPending: boolean;
  @Input() pagination: Pagination;
  @Output() changePage: EventEmitter<number> = new EventEmitter();

  ngOnInit(): void {
  }

}
