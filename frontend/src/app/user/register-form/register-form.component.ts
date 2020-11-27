import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ERROR_MESSAGE, ERROR_SNACKBAR_OPTIONS, SNACKBAR_CLOSE, SUCCESS_SNACKBAR_OPTIONS } from 'src/app/utils/constants';
import { FormValidatorService } from '../services/form-validator.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.sass']
})
export class RegisterFormComponent implements OnInit {

  constructor(
    private userService: UserService, 
    private formValidator: FormValidatorService,
    private snackBar: MatSnackBar
    ) { }

  registerPending: boolean = false;
  registerForm: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.pattern(new RegExp("\\S"))], 
    [this.formValidator.hasEmail(null)]), 
    firstName: new FormControl('', [Validators.required, Validators.pattern(new RegExp("\\S"))]), 
    lastName: new FormControl('', [Validators.required, Validators.pattern(new RegExp("\\S"))]), 
    password: new FormControl('', [Validators.required, Validators.pattern(new RegExp("\\S"))]), 
    passwordConfirmation: new FormControl('', [this.formValidator.passwordConfirmed()])
  });

  register(): void{
    if (this.registerForm.invalid){
      return;
    }
    this.registerPending = true;
    this.userService.register(this.registerForm.value).subscribe(
      () => {
        this.registerPending = false;
        this.snackBar.open("Your request has been sent! Check your email.", 
        SNACKBAR_CLOSE, SUCCESS_SNACKBAR_OPTIONS);
      }, 
      () => {
        this.registerPending = false;
        this.snackBar.open(ERROR_MESSAGE, SNACKBAR_CLOSE, ERROR_SNACKBAR_OPTIONS);
      }
    )
  }

  ngOnInit(): void {
    this.registerForm.get('password').valueChanges.subscribe(
      () => {
        this.registerForm.get('passwordConfirmation').updateValueAndValidity();
      }
    )
  }
  
}
