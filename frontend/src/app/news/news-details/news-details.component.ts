import { Component, Input, OnInit } from '@angular/core';
import { AuthService } from 'src/app/utils/services/auth.service';
import { News } from '../utils/news';

@Component({
  selector: 'app-news-details',
  templateUrl: './news-details.component.html',
  styleUrls: ['./news-details.component.sass']
})
export class NewsDetailsComponent implements OnInit {

  constructor(
    private authService: AuthService
  ) { }

  @Input() currentNews: News;

  hasPreviousImage: boolean = false;;
  hasNextImage: boolean;
  currentImageIndex: number = 0;

  get role(): string{
    return this.authService.getUser()?.role;
  }

  previousImage(): void {
    this.currentImageIndex--;
    this.hasNextImage = true;

    if (this.currentImageIndex === 0)
      this.hasPreviousImage = false;
  }

  nextImage(): void {
    this.currentImageIndex++;
    this.hasPreviousImage = true;

    if (this.currentImageIndex === this.currentNews.images.length - 1)
      this.hasNextImage = false;
  }

  edit(): void {
  }

  delete(): void {
  }
  
  ngOnInit(): void {
    if (this.currentNews.images.length > 0)
      this.hasNextImage = true;
  }

}
