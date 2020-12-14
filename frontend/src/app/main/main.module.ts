import { NgModule } from '@angular/core';
import { MapComponent } from './map/map.component';
import { LayoutModule } from '../layout/layout.module';
import { AngularYandexMapsModule, YA_MAP_CONFIG } from 'angular8-yandex-maps';
import { HomeComponent } from './home/home.component';
import { ToolbarComponent } from './toolbar/toolbar.component';
import { YANDEX_MAP_CONFIG } from '../constants/yandex';
import { CulturalOffersModule } from '../cultural-offers/cultural-offers.module';
import { CatsTypesModule } from '../cats-types/cats-types.module';
import { ProfileComponent } from './profile/profile.component';

@NgModule({
  declarations: [
    ToolbarComponent,
    MapComponent,
    ProfileComponent,
    HomeComponent,
  ],
  imports: [
    AngularYandexMapsModule.forRoot(YANDEX_MAP_CONFIG),
    LayoutModule,
    CulturalOffersModule,
    CatsTypesModule
  ],
  exports: [
    HomeComponent
  ],
  providers: [{
    provide: YA_MAP_CONFIG,
    useValue: YANDEX_MAP_CONFIG
  }]
})
export class MainModule { }
