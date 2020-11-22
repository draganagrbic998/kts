import { Component, EventEmitter, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AuthService } from 'src/app/utils/services/auth.service';
import { CulturalOffer } from '../utils/cultural-offer';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserFollowingService } from '../services/user-following.service';
import { CulturalService } from '../services/cultural.service';
import { ERROR_SNACKBAR_OPTIONS, FIRST_PAGE_HEADER, LAST_PAGE_HEADER, SUCCESS_SNACKBAR_OPTIONS } from 'src/app/utils/constants';
import { ConfirmationDialogComponent } from 'src/app/layout/confirmation-dialog/confirmation-dialog.component';
import { News } from 'src/app/news/utils/news';
import { NewsService } from 'src/app/news/services/news.service';
import { HttpHeaders, HttpResponse } from '@angular/common/http';


@Component({
  selector: 'app-cultural-dialog',
  templateUrl: './cultural-dialog.component.html',
  styleUrls: ['./cultural-dialog.component.sass']
})
export class CulturalDialogComponent implements OnInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) public culturalOffer: CulturalOffer, 
    private authService: AuthService,
    private userFollowingService: UserFollowingService,
    private culturalService: CulturalService,
    private newsService: NewsService,
    public dialogRef: MatDialogRef<CulturalDialogComponent>,
    private snackBar: MatSnackBar,
    public dialog: MatDialog
  ) { }

  toggleSubPending: boolean = false;
  toggleDelPending: boolean = false;

  onRefreshData: EventEmitter<CulturalOffer | number> = new EventEmitter();

  news: News[] = undefined;
  fetchPending: boolean = true;
  pageNumber: number = 0;
  endOfPages: boolean = true;
  startOfPages: boolean = true;

  get role(): string{
    return this.authService.getUser()?.role;
  }

  get isSubscribedTo(): boolean{
    return this.culturalOffer?.followed;
  }

  toggleSubscription(): void{
    this.toggleSubPending = true;
    
    this.userFollowingService.toggleSubscription(this.culturalOffer.id).subscribe(
      () => {
        this.toggleSubPending = false;

        if(!this.culturalOffer.followed) {
          this.culturalOffer.followed = true;
          this.onRefreshData.emit(this.culturalOffer);
        }
        else {
          this.culturalOffer.followed = false;
          this.onRefreshData.emit(this.culturalOffer.id);
        }

        if (this.culturalOffer)
          this.snackBar.open("You have successfully subscribed!", "Close", SUCCESS_SNACKBAR_OPTIONS);
        else
          this.snackBar.open("You have successfully unsubscribed!", "Close", SUCCESS_SNACKBAR_OPTIONS);
      }, 
      () => {
        this.toggleSubPending = false;
        this.snackBar.open("An error occured! Try again.", 
        "Close", ERROR_SNACKBAR_OPTIONS);
      }
    )
  }

  edit(): void{

  }

  delete(): void{
    const dialog: MatDialogRef<ConfirmationDialogComponent> = this.dialog.open(ConfirmationDialogComponent, 
      {disableClose: true, panelClass: "no-padding", data: "Delete this offer?"});

      dialog.afterClosed().subscribe((result) => {
        if (result) {
          this.toggleDelPending = true;
    
          this.culturalService.delete(this.culturalOffer.id).subscribe(
            () => {
              this.toggleDelPending = false;

              this.onRefreshData.emit(this.culturalOffer.id);
        
              this.snackBar.open("You have successfully deleted this cultural offer!", "Close", SUCCESS_SNACKBAR_OPTIONS);

              this.dialogRef.close();
            }, 
            () => {
              this.toggleDelPending = false;
              this.snackBar.open("An error occured! Try again.", 
              "Close", ERROR_SNACKBAR_OPTIONS);
            }
          )
        }
      });
  }
  
  changePage(amount: number): void{
    this.pageNumber += amount;
    this.fetchData();
  }

  refreshData(response: News | number): void{
    if (typeof response !== "number"){
      const temp: number[] = this.news.map(n => n.id);
      const index: number = temp.indexOf(response.id);
      this.news.splice(index !== -1 ? index : 0, index !== -1 ? 1 : 0, response);  
    }
    else{
      this.news = this.news.filter(n => n.id !== response);
    }
  }

  fetchData(): void{
    this.fetchPending = true;
    this.news = [];

    this.newsService.fetch(this.culturalOffer.id, this.pageNumber).subscribe(
      (data: HttpResponse<News[]>) => {
        this.fetchPending = false;
        if (data){
          this.news = data.body;
          const headers: HttpHeaders = data.headers;
          this.endOfPages = headers.get(LAST_PAGE_HEADER) === "true" ? true : false;
          this.startOfPages = headers.get(FIRST_PAGE_HEADER) === "true" ? true : false;
        }
        else{
          this.news = [];
          this.endOfPages = true;
          this.startOfPages = true;
        }
      }
    );
  }

  ngOnInit(): void {
    this.fetchData();
  }

}
