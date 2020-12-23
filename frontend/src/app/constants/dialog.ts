import { MatSnackBarConfig } from '@angular/material/snack-bar';

export const SNACKBAR_CLOSE = 'Close';
export const ERROR_MESSAGE = 'An error occured! Try again.';

export const DIALOG_OPTIONS = {
    panelClass: 'no-padding',
    disableClose: true
};

export const SUCCESS_SNACKBAR_OPTIONS: MatSnackBarConfig = {
    horizontalPosition: 'center',
    verticalPosition: 'top',
    panelClass: 'snackbar-success',
    // duration: 2000
};

export const ERROR_SNACKBAR_OPTIONS: MatSnackBarConfig = {
    horizontalPosition: 'center',
    verticalPosition: 'top',
    panelClass: 'snackbar-error',
    // duration: 2000
};
