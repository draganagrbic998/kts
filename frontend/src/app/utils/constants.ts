import { MatSnackBarConfig } from '@angular/material/snack-bar'

export const SUCCESS_SNACKBAR_OPTIONS: MatSnackBarConfig = {
    horizontalPosition: 'center', 
    verticalPosition: 'top', 
    panelClass: 'snackbar-success', 
    duration: 2000
}

export const ERROR_SNACKBAR_OPTIONS: MatSnackBarConfig = {
    horizontalPosition: 'center', 
    verticalPosition: 'top', 
    panelClass: 'snackbar-error', 
    duration: 2000
}

export const ADMIN_ROLE: string = "admin";
export const GUEST_ROLE: string = "guest";

export const FIRST_PAGE_HEADER: string = "is-first-page";
export const LAST_PAGE_HEADER: string = "is-last-page";
export const SMALL_PAGE_SIZE: number = 10;
export const LARGE_PAGE_SIZE: number = 30;

