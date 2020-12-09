import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { FIRST_PAGE_HEADER, LAST_PAGE_HEADER } from 'src/app/constants/pagination';
import { GUEST_ROLE } from 'src/app/constants/roles';
import { CulturalOffer } from 'src/app/models/cultural-offer';
import { AuthService } from 'src/app/services/auth/auth.service';
import { CulturalService } from 'src/app/services/cultural-offer/cultural.service';
import { UserFollowingService } from 'src/app/services/user-following/user-following.service';

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

  selectedTab = 0;
  culturalOffers: CulturalOffer[][] = [[], []];
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

  changePage(value: number): void{
    this.pageNumber[this.selectedTab] += value;
    this.fetchData();
  }

  filterData(): void{
    this.pageNumber[this.selectedTab] = 0;
    this.fetchData();
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
          this.endOfPages[this.selectedTab] = headers.get(LAST_PAGE_HEADER) === 'true' ? true : false;
          this.startOfPages[this.selectedTab] = headers.get(FIRST_PAGE_HEADER) === 'true' ? true : false;
        }
        else{
          this.culturalOffers[this.selectedTab] = [];
          this.endOfPages[this.selectedTab] = true;
          this.startOfPages[this.selectedTab] = true;
        }
      }
    );

  }

  refreshData(response: CulturalOffer | number): void{
    if (typeof response === 'number'){
      this.culturalOffers[0] = this.culturalOffers[0].filter(co => co.id !== response);
    }
    else{
      const temp: number[] = this.culturalOffers[0].map(co => co.id);
      const index: number = temp.indexOf(response.id);
      this.culturalOffers[0].splice(index !== -1 ? index : 0, index !== -1 ? 1 : 0, response);
      if (this.guest){
        if (response.followed){
          this.culturalOffers[1].push(response);
        }
        else{
          this.culturalOffers[1] = this.culturalOffers[1].filter(co => co.id !== response.id);
        }
      }
    }
  }

  ngOnInit(): void {
    this.fetchData();
  }

}
