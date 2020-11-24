import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { CulturalOffer } from 'src/app/cultural-offers/utils/cultural-offer';
import { ProfileDetailsComponent } from 'src/app/user/components/profile-details/profile-details.component';
import { AuthService } from 'src/app/utils/services/auth.service';
import { USER_PATH } from 'src/app/utils/router';
import { CategoriesDialogComponent } from 'src/app/categories/categories-dialog/categories-dialog.component';

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

  @Output() onToggle: EventEmitter<null> = new EventEmitter();
  @Output() onAddOnMap: EventEmitter<CulturalOffer> = new EventEmitter();
  
  get role(): string{
    return this.authService.getUser()?.role;
  }

  signIn(): void{
    this.router.navigate([USER_PATH]);
  }

  categories(): void{
    this.dialog.open(CategoriesDialogComponent, {
      height:'500px',
      width: '500px',
      backdropClass: 'cdk-overlay-transparent-backdrop',
      panelClass: "no-padding",
  });
  }

  signOut(): void{
    this.authService.deleteUser();
    this.router.navigate([USER_PATH]);
  }

  profile(): void{
    this.dialog.open(ProfileDetailsComponent, {
        backdropClass: 'cdk-overlay-transparent-backdrop',
        panelClass: "no-padding", 
        position: {
            top: '30px', 
            right: '30px'
        }
    });
  }

  addOffer(): void{

  }
  
  ngOnInit(): void {
  }

}
