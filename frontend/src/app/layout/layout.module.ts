import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormContainerComponent } from './form-container/form-container.component';
import { SpinnerButtonComponent } from './spinner-button/spinner-button.component';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ImageInputComponent } from './image-input/image-input.component';
import { SpacerContainerComponent } from './spacer-container/spacer-container.component';
import { BoldTextComponent } from './bold-text/bold-text.component';
import { PreloaderComponent } from './preloader/preloader.component';

import { MatCardModule } from '@angular/material/card'
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatInputModule } from '@angular/material/input';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatTabsModule } from '@angular/material/tabs';
import { MatDialogModule } from '@angular/material/dialog';
import { CloseButtonComponent } from './close-button/close-button.component';
import { ConfirmationDialogComponent } from './confirmation-dialog/confirmation-dialog.component';


@NgModule({
  declarations: [
    FormContainerComponent,
    SpinnerButtonComponent,
    ImageInputComponent,
    SpacerContainerComponent,
    BoldTextComponent,
    PreloaderComponent,
    CloseButtonComponent,
    ConfirmationDialogComponent
  ],
  imports: [
    CommonModule,
    MatCardModule,
    MatProgressSpinnerModule,
    MatButtonModule,
    MatProgressSpinnerModule,
    MatIconModule
  ],
  exports: [
    CommonModule,
    FormContainerComponent,
    SpinnerButtonComponent,
    ReactiveFormsModule,
    FormsModule,
    RouterModule,
    ImageInputComponent,
    SpacerContainerComponent,
    BoldTextComponent,
    PreloaderComponent,
    CloseButtonComponent,

    MatSnackBarModule,
    MatFormFieldModule,
    MatButtonModule,
    MatToolbarModule,
    MatTooltipModule,
    MatIconModule,
    MatMenuModule,
    MatInputModule,
    MatCardModule,
    MatExpansionModule,
    MatAutocompleteModule,
    MatSidenavModule,
    MatTabsModule,
    MatDialogModule
  ]
})
export class LayoutModule { }
