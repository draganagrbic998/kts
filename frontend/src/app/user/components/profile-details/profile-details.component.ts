import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/utils/services/auth.service';
import { MatDialogRef } from '@angular/material/dialog';
import { User } from 'src/app/utils/user';
import { PROFILE_PATH } from '../../utils/router';

@Component({
  selector: 'app-profile-details',
  templateUrl: './profile-details.component.html',
  styleUrls: ['./profile-details.component.sass']
})
export class ProfileDetailsComponent implements OnInit {

  constructor(
    private authService: AuthService,
    private router: Router,
    private dialogRef: MatDialogRef<ProfileDetailsComponent>, 
    private route: ActivatedRoute
  ) { }

  profile: User = this.authService.getUser();

  edit(): void{
    this.dialogRef.close(); //ovo ce biti mali dialog koji cemo otvarati na mapi
    this.router.navigate([`../${PROFILE_PATH}`], {relativeTo: this.route});
  }

  ngOnInit(): void {
  }

}
