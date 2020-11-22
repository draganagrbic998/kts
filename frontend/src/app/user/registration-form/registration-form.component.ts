import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ERROR_SNACKBAR_OPTIONS, SUCCESS_SNACKBAR_OPTIONS } from 'src/app/utils/constants';
import { AuthService } from 'src/app/utils/services/auth.service';
import { FormValidatorService } from '../services/form-validator.service';
import { UserService } from '../services/user.service';


@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.sass']
})
export class RegistrationFormComponent implements OnInit {

  constructor(
    private authService: AuthService,
    private userService: UserService,
    private formValidator: FormValidatorService,
    private snackBar: MatSnackBar) { }

  registerPending: boolean = false;
  registerForm: FormGroup = new FormGroup({
    email: new FormControl("", [Validators.required, Validators.pattern(new RegExp("\\S"))], 
    [this.formValidator.hasEmail(null)]), 
    firstName: new FormControl("", [Validators.required, Validators.pattern(new RegExp("\\S"))]), 
    lastName: new FormControl("", [Validators.required, Validators.pattern(new RegExp("\\S"))]), 
    
    password: new FormControl(''),
    confirmPassword: new FormControl(''), 

  } );
  ngOnInit(): void {
  }
  register(): void{
    if (this.registerForm.invalid){
      return;
    }

   

    
    
    this.registerPending = true;
    this.userService.register(this.registerForm.value).subscribe(
      () => {
        this.registerPending = false;
        this.snackBar.open("Your profile has been updated!", "Close", SUCCESS_SNACKBAR_OPTIONS);
      }, 
      () => {
        this.registerPending = false;
        this.snackBar.open("An error occured! Try again.", 
        "Close", ERROR_SNACKBAR_OPTIONS);
      }
    )
  }

}
