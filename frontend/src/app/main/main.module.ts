import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MapComponent } from './map/map.component';
import { LayoutModule } from '../layout/layout.module';
import { AngularYandexMapsModule, YA_MAP_CONFIG } from 'angular8-yandex-maps';

@NgModule({
  declarations: [MapComponent],
  imports: [
    LayoutModule,
    AngularYandexMapsModule.forRoot(YANDEX_MAP_CONFIG),
  ],
  providers: [{
    provide: YA_MAP_CONFIG,
    useValue: YANDEX_MAP_CONFIG
  }]
})
export class MainModule { }
