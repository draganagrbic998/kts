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
