import { NgModule } from '@angular/core';
import { MapComponent } from './map/map.component';
import { LayoutModule } from '../layout/layout.module';
import { AngularYandexMapsModule, YA_MAP_CONFIG } from 'angular8-yandex-maps';
import { HomeComponent } from './home/home.component';
import { ToolbarComponent } from './toolbar/toolbar.component';
import { YANDEX_MAP_CONFIG } from './utils/yandex';
import { CulturalOffersModule } from '../cultural-offers/cultural-offers.module';
import { UserModule } from '../user/user.module';
import { CategoriesModule } from '../categories/categories.module';
import { TypesModule } from '../types/types.module';

@NgModule({
  declarations: [MapComponent, HomeComponent, ToolbarComponent],
  imports: [
    LayoutModule,
    AngularYandexMapsModule.forRoot(YANDEX_MAP_CONFIG),
    CulturalOffersModule, 
    CategoriesModule,
    TypesModule,
    UserModule
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
