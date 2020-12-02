import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialogRef } from '@angular/material/dialog';
import { User } from 'src/app/utils/user';
import { AuthService } from 'src/app/services/auth.service';
import { USER_PATH } from 'src/app/utils/router';
import { PROFILE_PATH } from 'src/app/user/utils/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.sass']
})
export class ProfileComponent implements OnInit {

  constructor(
    private authService: AuthService,
    private router: Router,
    private dialogRef: MatDialogRef<ProfileComponent>
  ) { }

  profile: User = this.authService.getUser();

  edit(): void{
    this.dialogRef.close();
    this.router.navigate([`${USER_PATH}/${PROFILE_PATH}`]);
  }

  ngOnInit(): void {
  }

}
