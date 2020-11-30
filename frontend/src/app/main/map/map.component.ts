import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { CulturalDialogComponent } from 'src/app/cultural-offers/cultural-dialog/cultural-dialog.component';
import { CulturalOffer } from 'src/app/cultural-offers/utils/cultural-offer';
import { DIALOG_OPTIONS } from 'src/app/utils/constants';
import { DEFAULT_MAP_CENTER } from '../utils/constants';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.sass']
})
export class MapComponent implements OnInit {

  constructor(
    private dialog: MatDialog
  ) { }

  @Input() culturalOffers: CulturalOffer[];
  @Input() fetchPending: boolean;
  @Output() onRefreshData: EventEmitter<CulturalOffer | number> = new EventEmitter();

  mapPending: boolean = true;
  center: CulturalOffer;
  ymaps;

  get mapCenter(): number[]{
    if (this.center){
      return [this.center.lat, this.center.lng];
    }
    return DEFAULT_MAP_CENTER;
  }

  markOnMap(culturalOffer: CulturalOffer): void{
    this.center = culturalOffer;
    this.ymaps.instance.balloon.open(this.mapCenter, 
      `<div style='text-align: center; font-weight: bold;'>${culturalOffer.name} is placed here!</div>`);
  }

  showDetails(culturalOffer: CulturalOffer): void{
    const options = {...DIALOG_OPTIONS, ...{data: culturalOffer}}
    const dialog: MatDialogRef<CulturalDialogComponent> = this.dialog.open(CulturalDialogComponent, options);
    dialog.componentInstance.onRefreshData.subscribe(
      (response: CulturalOffer | number) => {
        if (typeof response !== "number"){
          this.markOnMap(response);
        }
        this.onRefreshData.emit(response);
      }
    );
  }

  placemarkOptions(culturalOffer: CulturalOffer){
    return {
      iconLayout: 'default#image',
      iconImageHref: culturalOffer.placemarkIcon
    }
  }

  placemarkProperties(culturalOffer: CulturalOffer){
    
    return {
      hintContent: `
        <div style="text-align: center;">
          <div>
            <img src='${culturalOffer.image}' height="100" width="100" alt='no image'>
          </div>
          <div style="font-weight: bold">
          ${culturalOffer.name}
          </div>
          <div>
            ${culturalOffer.location}
          </div>
        </div>
      `
    }
  }

  ngOnInit(): void {
  }

}
