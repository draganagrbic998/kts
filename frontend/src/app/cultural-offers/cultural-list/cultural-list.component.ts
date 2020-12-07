import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Observable, of } from 'rxjs';
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
  @Output() changePage: EventEmitter<number> = new EventEmitter();
  @Output() filterData: EventEmitter<null> = new EventEmitter();
  @Output() markOnMap: EventEmitter<CulturalOffer> = new EventEmitter();

  names: Observable<string[]>;
  locations: Observable<string[]>;
  types: Observable<string[]>;

  fetchNames(): void{
    const value: string = this.filterForm.get('name').value.trim().toLowerCase();
    this.names = value.length ? this.culturalService.filterNames(value) : of([]);
  }

  fetchLocations(): void{
    const value: string = this.filterForm.get('location').value.trim().toLowerCase();
    this.locations = value.length ? this.culturalService.filterLocations(value) : of([]);
  }

  fetchTypes(): void{
    const value: string = this.filterForm.get('type').value.trim().toLowerCase();
    this.types = value.length ? this.culturalService.filterTypes(value) : of([]);
  }

  ngOnInit(): void {
  }

}
