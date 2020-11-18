import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { CulturalService } from '../services/cultural.service';
import { CulturalOffer } from '../utils/cultural-offer';

@Component({
  selector: 'app-cultural-list',
  templateUrl: './cultural-list.component.html',
  styleUrls: ['./cultural-list.component.sass']
})
export class CulturalListComponent implements OnInit {

  constructor(
    private culturalService: CulturalService
  ) { }

  @Input() title: string;
  @Input() culturalOffers: CulturalOffer[];
  @Input() fetchPending: boolean;
  @Input() hasPrevious: boolean;
  @Input() hasNext: boolean;
  @Input() filterForm: FormGroup;
  @Output() onChangePage: EventEmitter<number> = new EventEmitter();
  @Output() onFilterData: EventEmitter<null> = new EventEmitter();
  @Output() onMarkOnMap: EventEmitter<CulturalOffer> = new EventEmitter();   

  names: Observable<string[]>;
  locations: Observable<string[]>;
  types: Observable<string[]>;
  panelOpenState: boolean = false;

  

  ngOnInit(): void {
  }

}
