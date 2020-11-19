import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AuthService } from 'src/app/utils/services/auth.service';
import { CulturalOffer } from '../utils/cultural-offer';

@Component({
  selector: 'app-cultural-dialog',
  templateUrl: './cultural-dialog.component.html',
  styleUrls: ['./cultural-dialog.component.sass']
})
export class CulturalDialogComponent implements OnInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) public culturalOffer: CulturalOffer, 
    private authService: AuthService,
    public dialogRef: MatDialogRef<CulturalDialogComponent>
  ) { }

  get role(): string{
    return this.authService.getUser()?.role;
  }

  edit(): void{

  }

  delete(): void{
    
  }
  
  ngOnInit(): void {
  }


}
