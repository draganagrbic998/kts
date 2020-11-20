import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { CulturalDialogComponent } from 'src/app/cultural-offers/cultural-dialog/cultural-dialog.component';
import { CulturalOffer } from 'src/app/cultural-offers/utils/cultural-offer';
import { DEFAULT_MAP_CENTER } from '../utils/yandex';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.sass']
})
export class MapComponent implements OnInit {

  constructor(
    private dialog: MatDialog
  ) { }

  @Input() fetchPending: boolean;
  @Input() culturalOffers: CulturalOffer[];
  @Output() onToggleSubscription: EventEmitter<CulturalOffer | number> = new EventEmitter();

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
    console.log(this.ymaps);
    this.center = culturalOffer;
    this.ymaps.instance.balloon.open(this.mapCenter, "<div style='text-align: center; font-weight: bold;'>found!</div>");
  }

  showDetails(culturalOffer: CulturalOffer): void{
    const dialog: MatDialogRef<CulturalDialogComponent> = this.dialog.open(CulturalDialogComponent, 
      {disableClose: true, panelClass: "no-padding", data: culturalOffer});
      const sub = dialog.componentInstance.onToggleSubscription.subscribe((response: CulturalOffer | number) => {
        this.onToggleSubscription.emit(response);
      });
      dialog.afterClosed().subscribe(() => {
        // unsubscribe onAdd
      });
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
            <img src='${culturalOffer.image}' alt='no image' height="100" width="100">
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

  balloonProperties(){
    return {
      balloonContentHeader: `<div style='text-align: center'>found!</div>`,
      balloonContentBody: `<div style='text-align: center'>is placed here!</div>`
    }
  }

  ngOnInit(): void {
  }

}
