import { Component, OnInit } from '@angular/core';
import { Photo } from '../model/photo.model';
import { Photographe } from '../model/photographe.model';
import { Tag } from '../model/tag.model';
import { PhotoService } from '../service/photo.service';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable } from 'rxjs';
import { PhotoListComponent } from '../photo-list/photo-list.component';

@Component({
  selector: 'app-photo',
  templateUrl: './photo.component.html',
  styleUrls: ['./photo.component.css']
})
export class PhotoComponent implements OnInit {


  photoListe : Observable<Photo[]>;
   

  constructor(
    private photoService: PhotoService,
    private router: Router,
    ) {
     }

  ngOnInit() {

    this.photoListe = this.photoService.getPhotos();
    
    
  }

  onSelectImage(photoId : number){

    this.photoService.findPhotoById(photoId);
    this.router.navigate(['/photodetails/' +photoId]);
  }

  

}
