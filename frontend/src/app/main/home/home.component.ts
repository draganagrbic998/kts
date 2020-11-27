import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { CulturalService } from 'src/app/cultural-offers/services/cultural.service';
import { CulturalOffer } from 'src/app/cultural-offers/utils/cultural-offer';
import { FIRST_PAGE_HEADER, LAST_PAGE_HEADER, GUEST_ROLE } from 'src/app/utils/constants';
import { UserFollowingService } from 'src/app/cultural-offers/services/user-following.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.sass']
})
export class HomeComponent implements OnInit {

  constructor(
    private authService: AuthService, 
    private culturalService: CulturalService, 
    private userFollowingService: UserFollowingService
  ) { }

  selectedTab: number = 0;
  culturalOffers: CulturalOffer[][] = [undefined, undefined];
  fetchPending: boolean[] = [true, true];
  pageNumber: number[] = [0, 0];
  startOfPages: boolean[] = [true, true];
  endOfPages: boolean[] = [true, true];
  filterForm: FormGroup[] = [
    new FormGroup({
      name: new FormControl(''), 
      location: new FormControl(''), 
      type: new FormControl('')
    }), 
    new FormGroup({
      name: new FormControl(''), 
      location: new FormControl(''), 
      type: new FormControl('')
    })
  ];

  get guest(): boolean{
    return this.authService.getUser()?.role === GUEST_ROLE;
  }

  changeTab(index: number): void{
    this.selectedTab = index;
    if (this.fetchPending[this.selectedTab]){
      this.fetchData();
    }
  }

  changePage(amount: number): void{
    this.pageNumber[this.selectedTab] += amount;
    this.fetchData();
  }

  filterData(): void{
    this.pageNumber[this.selectedTab] = 0;
    this.fetchData();
  }

  refreshData(response: CulturalOffer | number): void{
    const selectedTab = this.guest ? 1 : 0;
    if (typeof response === "number"){
      this.culturalOffers[selectedTab] = this.culturalOffers[selectedTab].filter(co => co.id !== response);
    }
    else{
      const temp: number[] = this.culturalOffers[selectedTab].map(co => co.id);
      const index: number = temp.indexOf(response.id);
      this.culturalOffers[selectedTab].splice(index !== -1 ? index : 0, index !== -1 ? 1 : 0, response);  
    }
  }

  fetchData(): void{

    this.fetchPending[this.selectedTab] = true;
    this.culturalOffers[this.selectedTab] = [];
    const selectedService = this.selectedTab ? this.userFollowingService : this.culturalService;
    selectedService.filter(this.filterForm[this.selectedTab].value, this.pageNumber[this.selectedTab]).subscribe(
      (data: HttpResponse<CulturalOffer[]>) => {
        this.fetchPending[this.selectedTab] = false;
        if (data){
          this.culturalOffers[this.selectedTab] = data.body;
          const headers: HttpHeaders = data.headers;
          this.endOfPages[this.selectedTab] = headers.get(LAST_PAGE_HEADER) === "true" ? true : false;
          this.startOfPages[this.selectedTab] = headers.get(FIRST_PAGE_HEADER) === "true" ? true : false;
        }
        else{
          this.endOfPages[this.selectedTab] = true;
          this.startOfPages[this.selectedTab] = true;
        }
      }
    );
    
  }

  ngOnInit(): void {
    this.fetchData();
  }

}
