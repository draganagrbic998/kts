import { Component, Input, OnInit } from '@angular/core';
import { Image } from 'src/app/models/image';
import { ImageService } from 'src/app/services/image/image.service';

@Component({
  selector: 'app-images-input',
  templateUrl: './images-input.component.html',
  styleUrls: ['./images-input.component.sass']
})
export class ImagesInputComponent implements OnInit {

  constructor(
    private imageService: ImageService
  ) { }

  @Input() images: Image[];

  addImage(upload: Blob): void{
    this.imageService.getBase64(upload)
    .then((path: string) => {
      this.images.push({path, upload});
    });
  }

  ngOnInit(): void {
  }

}
