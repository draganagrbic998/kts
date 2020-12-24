import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { CatTypeDialogComponent } from 'src/app/cats-types/cat-type-dialog/cat-type-dialog.component';
import { DIALOG_OPTIONS } from 'src/app/constants/dialog';
import { LOGIN_PATH, USER_PATH } from 'src/app/constants/router';
import { CulturalFormComponent } from 'src/app/cultural-offers/cultural-form/cultural-form.component';
import { CulturalOffer } from 'src/app/models/cultural-offer';
import { AuthService } from 'src/app/shared/services/auth/auth.service';
import { ProfileDetailsComponent } from '../../user/profile/profile.component';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.sass']
})
export class ToolbarComponent implements OnInit {

  constructor(
    private authService: AuthService,
    private router: Router,
    private dialog: MatDialog
  ) { }

  @Output() toggle: EventEmitter<null> = new EventEmitter();
  @Output() refreshData: EventEmitter<CulturalOffer> = new EventEmitter();

  get role(): string{
    return this.authService.getUser()?.role;
  }

  signIn(): void{
    this.router.navigate([`${USER_PATH}/${LOGIN_PATH}`]);
  }

  signOut(): void{
    this.authService.deleteUser();
    this.router.navigate([`${USER_PATH}/${LOGIN_PATH}`]);
  }

  profile(): void{
    this.dialog.open(ProfileDetailsComponent, {
        panelClass: 'no-padding',
        backdropClass: 'cdk-overlay-transparent-backdrop',
        position: {
            top: '30px',
            right: '30px'
        }
    });
  }

  categories(): void{
    const options = {...DIALOG_OPTIONS, ...{width: '500px', height: '500px'}, data: true};
    this.dialog.open(CatTypeDialogComponent, options);
  }

  types(): void{
    const options = {...DIALOG_OPTIONS, ...{width: '500px', height: '550px'}, data: false};
    this.dialog.open(CatTypeDialogComponent, options);
  }

  addOffer(): void{
    const options = {...DIALOG_OPTIONS, ...{data: {}}};
    this.dialog.open(CulturalFormComponent, options);
  }

  ngOnInit(): void {
  }

}
