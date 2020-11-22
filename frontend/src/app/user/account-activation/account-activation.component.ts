import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { ERROR_SNACKBAR_OPTIONS, SUCCESS_SNACKBAR_OPTIONS } from 'src/app/utils/constants';
import { UserService } from '../services/user.service';
import { LOGIN_PATH } from '../utils/router';

@Component({
  selector: 'app-account-activation',
  templateUrl: './account-activation.component.html',
  styleUrls: ['./account-activation.component.sass']
})
export class AccountActivationComponent implements OnInit {

  constructor(private router: Router,private route: ActivatedRoute,private userService: UserService,private snackBar: MatSnackBar
    ) { }

  activatePending: boolean = true;

  ngOnInit(): void {
    const id:string = this.route.snapshot.paramMap.get('code');
    console.log(id);
    this.userService.activation(id).subscribe(
      () => {
        this.activatePending = false;
        this.router.navigate([LOGIN_PATH]);
        this.snackBar.open("Successfully registered!", 
        "Close", SUCCESS_SNACKBAR_OPTIONS);
      },
      () =>{
        this.activatePending = false;
        this.snackBar.open("An error occured! Try again.", 
        "Close", ERROR_SNACKBAR_OPTIONS);
      }

    );
  }

  

}
