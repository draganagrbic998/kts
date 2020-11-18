import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Observable, of } from 'rxjs';
import { CulturalService } from '../services/cultural.service';
import { AUTOCOMPLETE_LENGTH } from '../utils/constants';
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

  fetchNames(): void{
    const value: string = this.filterForm.get('name').value.trim().toLowerCase();
    if (value.length >= AUTOCOMPLETE_LENGTH){
      this.names = this.culturalService.filterNames(value);
    }
    else{
      this.names = of([]);
    }
  }

  fetchLocations(): void{
    const value: string = this.filterForm.get('location').value.trim().toLowerCase();
    if (value.length >= AUTOCOMPLETE_LENGTH){
      this.locations = this.culturalService.filterLocations(value);
    }
    else{
      this.locations = of([]);
    }
  }

  fetchTypes(): void{
    const value: string = this.filterForm.get('type').value.trim().toLowerCase();
    if (value.length >= AUTOCOMPLETE_LENGTH){
      this.types = this.culturalService.filterTypes(value);
    }
    else{
      this.types = of([]);
    }
  }

  ngOnInit(): void {
  }

}
