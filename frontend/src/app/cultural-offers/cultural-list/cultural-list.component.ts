import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Observable, of, Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import { AUTOCOMPLETE_DEBOUNCE, AUTOCOMPLETE_LENGTH } from 'src/app/constants/autocomplete';
import { CulturalOffer } from 'src/app/models/cultural-offer';
import { CulturalService } from 'src/app/services/cultural-offer/cultural.service';

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
  @Output() refreshData: EventEmitter<CulturalOffer | number> = new EventEmitter();

  nameFilters: Subject<string> = new Subject<string>();
  locationFilters: Subject<string> = new Subject<string>();
  typeFilters: Subject<string> = new Subject<string>();

  names: Observable<string[]> = this.nameFilters.pipe(
    debounceTime(AUTOCOMPLETE_DEBOUNCE),
    distinctUntilChanged(),
    switchMap((filter: string) => filter.length >= AUTOCOMPLETE_LENGTH ?
    this.culturalService.filterNames(filter) : of([]))
  );
  locations: Observable<string[]> = this.locationFilters.pipe(
    debounceTime(AUTOCOMPLETE_DEBOUNCE),
    distinctUntilChanged(),
    switchMap((filter: string) => filter.length >= AUTOCOMPLETE_LENGTH ?
    this.culturalService.filterLocations(filter) : of([]))
  );
  types: Observable<string[]> = this.typeFilters.pipe(
    debounceTime(AUTOCOMPLETE_DEBOUNCE),
    distinctUntilChanged(),
    switchMap((filter: string) => filter.length >= AUTOCOMPLETE_LENGTH ?
    this.culturalService.filterTypes(filter) : of([]))
  );

  ngOnInit(): void {
  }

}
