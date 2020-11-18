import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { CulturalService } from 'src/app/cultural-offers/services/cultural.service';
import { CulturalOffer } from 'src/app/cultural-offers/utils/cultural-offer';
import { AuthService } from 'src/app/utils/services/auth.service';
import { FIRST_PAGE_HEADER, LAST_PAGE_HEADER, GUEST_ROLE } from 'src/app/utils/constants';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.sass']
})
export class HomeComponent implements OnInit {

  constructor(
    private authService: AuthService, 
    private culturalService: CulturalService
  ) { }

  selectedTag: number = 0;
  culturalOffers: CulturalOffer[][] = [undefined, undefined];
  fetchPending: boolean[] = [true, true];
  pageNumber: number[] = [0, 0];
  endOfPages: boolean[] = [true, true];
  startOfPages: boolean[] = [true, true];
  title: string[] = [
    "All Cultural Offers", 
    "Followed Cultural Offers"
  ];
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

  changePage(amount: number): void{
    this.pageNumber[this.selectedTag] += amount;
    this.fetchData();
  }

  filterData(): void{
    this.pageNumber[this.selectedTag] = 0;
    this.fetchData();
  }

  refreshData(response: CulturalOffer | number): void{
    if (this.guest && this.selectedTag === 0){
      return;
    }
    if (typeof response !== "number"){
      const temp: number[] = this.culturalOffers[this.selectedTag].map(co => co.id);
      const index: number = temp.indexOf(response.id);
      this.culturalOffers[this.selectedTag].splice(index !== -1 ? index : 0, index !== -1 ? 1 : 0, response);  
    }
    else{
      this.culturalOffers[this.selectedTag] = this.culturalOffers[this.selectedTag].filter(co => co.id !== response);
    }
  }

  fetchData(): void{

    this.fetchPending[this.selectedTag] = true;
    this.culturalOffers[this.selectedTag] = [];
    const selectedService = this.selectedTag ? null : this.culturalService;
    if (selectedService === null){
      return;
    }

    selectedService.filter(this.filterForm[this.selectedTag].value, this.pageNumber[this.selectedTag]).subscribe(
      (data: HttpResponse<CulturalOffer[]>) => {
        this.fetchPending[this.selectedTag] = false;
        if (data){
          this.culturalOffers[this.selectedTag] = data.body;
          const headers: HttpHeaders = data.headers;
          this.endOfPages[this.selectedTag] = headers.get(LAST_PAGE_HEADER) === "true" ? true : false;
          this.startOfPages[this.selectedTag] = headers.get(FIRST_PAGE_HEADER) === "true" ? true : false;
        }
        else{
          this.culturalOffers[this.selectedTag] = [];
          this.endOfPages[this.selectedTag] = true;
          this.startOfPages[this.selectedTag] = true;
        }
      }
    );
    
  }

  ngOnInit(): void {
    this.fetchData();
  }

}
