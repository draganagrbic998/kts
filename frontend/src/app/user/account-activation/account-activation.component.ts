import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { SNACKBAR_CLOSE, SUCCESS_SNACKBAR_OPTIONS } from 'src/app/constants/dialog';
import { LOGIN_PATH, USER_PATH } from 'src/app/constants/router';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-account-activation',
  templateUrl: './account-activation.component.html',
  styleUrls: ['./account-activation.component.sass']
})
export class AccountActivationComponent implements OnInit {

  constructor(
    private userService: UserService,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar
  ) { }

  activatePending = true;

  ngOnInit(): void {
    const code: string = this.route.snapshot.params.code;
    this.userService.activate(code).subscribe(
      () => {
        this.activatePending = false;
        this.router.navigate([`${USER_PATH}/${LOGIN_PATH}`]);
        this.snackBar.open('Your account has been activated! You can login now.',
        SNACKBAR_CLOSE, SUCCESS_SNACKBAR_OPTIONS);
      },
      () => {
        this.activatePending = false;
      }
    );
  }

}
