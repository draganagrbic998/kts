import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ERROR_SNACKBAR_OPTIONS } from 'src/app/utils/constants';
import { AuthService } from 'src/app/utils/services/auth.service';
import { User } from 'src/app/utils/user';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.sass']
})
export class LoginFormComponent implements OnInit {

  constructor(
    private authService: AuthService,
    private userService: UserService,
    private snackBar: MatSnackBar
  ) { }

  loginPending: boolean = false;
  loginForm: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required])
  });

  login(): void{
    if (this.loginForm.invalid){
      return;
    }
    this.loginPending = true;
    this.userService.signIn(this.loginForm.value).subscribe(
      (user: User) => {
        this.loginPending = false;
        this.authService.saveUser(user);
      }, 
      () => {
        this.loginPending = false;
        this.snackBar.open("Email or password isn't correct! Try again.",
        "Close", ERROR_SNACKBAR_OPTIONS);
      }
    );
  }

  ngOnInit(): void {
  }

}
