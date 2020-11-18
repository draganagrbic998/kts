import { Component, Input, OnInit } from '@angular/core';
import { CulturalOffer } from 'src/app/cultural-offers/utils/cultural-offer';
import { DEFAULT_MAP_CENTER } from '../utils/yandex';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.sass']
})
export class MapComponent implements OnInit {

  constructor() { }

  @Input() fetchPending: boolean;
  @Input() culturalOffers: CulturalOffer[];

  mapPending: boolean = true;
  center: CulturalOffer;
  balloon: boolean = false;

  get mapCenter(): number[]{
    if (this.center){
      return [this.center.lat, this.center.lng];
    }
    return DEFAULT_MAP_CENTER;
  }

  markOnMap(culturalOffer: CulturalOffer): void{
    this.balloon = false;
    this.center = culturalOffer;
    this.balloon = true;
  }

  showDetails(culturalOffer: CulturalOffer): void{
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
      balloonContentHeader: `<div style='text-align: center'>${this.center.type} found!</div>`,
      balloonContentBody: `<div style='text-align: center'>${this.center.name} is placed here!</div>`
    }
  }

  openBalloon(event): void{
    console.log(event.instance.balloon.open);
    event.instance.balloon.open();
  }

  closeBalloon(event): void{
    if (event.type === "balloonclose"){
      this.balloon = false;
    }
  }

  ngOnInit(): void {
  }

}
