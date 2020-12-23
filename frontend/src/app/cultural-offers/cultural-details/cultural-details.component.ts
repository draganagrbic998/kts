import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { DIALOG_OPTIONS } from 'src/app/constants/dialog';
import { CulturalOffer } from 'src/app/models/cultural-offer';
import { CulturalDialogComponent } from '../cultural-dialog/cultural-dialog.component';

@Component({
  selector: 'app-cultural-details',
  templateUrl: './cultural-details.component.html',
  styleUrls: ['./cultural-details.component.sass']
})
export class CulturalDetailsComponent implements OnInit {

  constructor(
    private dialog: MatDialog
  ) { }

  @Input() culturalOffer: CulturalOffer;
  @Output() markOnMap: EventEmitter<CulturalOffer> = new EventEmitter();
  refreshData: EventEmitter<CulturalOffer | number> = new EventEmitter();

  showDetails(): void{
    const options = {...DIALOG_OPTIONS, ...{data: this.culturalOffer}};
    const dialog: MatDialogRef<CulturalDialogComponent> = this.dialog.open(CulturalDialogComponent, options);
    dialog.componentInstance.onRefreshData.subscribe(
      (response: CulturalOffer | number) => {
        this.refreshData.emit(response);
      }
    );
  }

  ngOnInit(): void {
  }

}
