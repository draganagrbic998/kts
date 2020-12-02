import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { BoldTextComponent } from './bold-text/bold-text.component';
import { CloseButtonComponent } from './close-button/close-button.component';
import { DeleteConfirmationComponent } from './delete-confirmation/delete-confirmation.component';
import { FormContainerComponent } from './form-container/form-container.component';
import { ImageInputComponent } from './image-input/image-input.component';
import { PreloaderComponent } from './preloader/preloader.component';
import { SpacerContainerComponent } from './spacer-container/spacer-container.component';
import { SpinnerButtonComponent } from './spinner-button/spinner-button.component';
import { PaginatorComponent } from './paginator/paginator.component';
import { ImageComponent } from './image/image.component';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatTabsModule } from '@angular/material/tabs';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { CarouselModule } from 'ng-uikit-pro-standard';
import { EmptyContainerComponent } from './empty-container/empty-container.component';

@NgModule({
  declarations: [
    BoldTextComponent,
    CloseButtonComponent,
    DeleteConfirmationComponent,
    FormContainerComponent,
    ImageInputComponent,
    PreloaderComponent,
    SpacerContainerComponent,
    SpinnerButtonComponent,
    PaginatorComponent,
    ImageComponent,
    EmptyContainerComponent
  ],
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatProgressSpinnerModule,
    MatIconModule,
    MatTooltipModule
  ],
  exports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,

    BoldTextComponent,
    CloseButtonComponent,
    DeleteConfirmationComponent,
    FormContainerComponent,
    ImageInputComponent,
    PreloaderComponent,
    SpacerContainerComponent,
    SpinnerButtonComponent,
    PaginatorComponent,
    ImageComponent,
    EmptyContainerComponent,

    MatFormFieldModule,
    MatInputModule,
    MatToolbarModule,
    MatTooltipModule,
    MatIconModule,
    MatMenuModule,
    MatSidenavModule,
    MatTabsModule,
    MatDialogModule,
    MatButtonModule,
    MatCardModule,
    MatAutocompleteModule,
    MatProgressSpinnerModule,
    MatSnackBarModule,
    MatExpansionModule,
    MatDatepickerModule,
    MatNativeDateModule,
    CarouselModule
  ]
})
export class LayoutModule { }
