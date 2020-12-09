import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialogRef } from '@angular/material/dialog';
import { AuthService } from 'src/app/services/auth/auth.service';
import { User } from 'src/app/models/user';
import { PROFILE_PATH, USER_PATH } from 'src/app/constants/router';

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
