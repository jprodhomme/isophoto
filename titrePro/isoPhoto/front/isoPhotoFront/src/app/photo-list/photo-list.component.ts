import { Component, OnInit } from '@angular/core';
import { PhotoService } from '../service/photo.service';
import { environment } from 'src/environments/environment';
import * as jwt_decode from 'jwt-decode';
import { Photo } from '../model/photo.model';
import { BehaviorSubject, Observable } from 'rxjs';

@Component({
  selector: 'app-photo-list',
  templateUrl: './photo-list.component.html',
  styleUrls: ['./photo-list.component.css']
})
export class PhotoListComponent implements OnInit {

  constructor(private photoService : PhotoService) { }

  pseudo : string;
  photoListe : Observable<Photo[]>;

  ngOnInit() {
   
    const decodedToken = jwt_decode(sessionStorage.getItem(environment.accessToken));
    
    const pseudo = decodedToken.pseudo;

    this.photoListe = this.photoService.findPhotoByPhotographe(pseudo);
    

  }
}


