import { MatSnackBarConfig } from '@angular/material/snack-bar';

export const ADMIN_ROLE = 'admin';
export const GUEST_ROLE = 'guest';
export const FIRST_PAGE_HEADER = 'first-page';
export const LAST_PAGE_HEADER = 'last-page';
export const SMALL_PAGE_SIZE = 10;
export const LARGE_PAGE_SIZE = 30;
export const ERROR_MESSAGE = 'An error occured! Try again.';
export const SNACKBAR_CLOSE = 'Close';

export const DIALOG_OPTIONS = {
    panelClass: 'no-padding',
    disableClose: true
};

export const SUCCESS_SNACKBAR_OPTIONS: MatSnackBarConfig = {
    horizontalPosition: 'center',
    verticalPosition: 'top',
    panelClass: 'snackbar-success',
    duration: 2000
};

export const ERROR_SNACKBAR_OPTIONS: MatSnackBarConfig = {
    horizontalPosition: 'center',
    verticalPosition: 'top',
    panelClass: 'snackbar-error',
    duration: 2000
};
