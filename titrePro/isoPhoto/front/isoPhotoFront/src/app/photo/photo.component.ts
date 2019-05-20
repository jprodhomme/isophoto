import { Component, OnInit } from '@angular/core';
import { Photo } from '../model/photo.model';
import { Photographe } from '../model/photographe.model';
import { Tag } from '../model/tag.model';
import { PhotoService } from '../service/photo.service';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-photo',
  templateUrl: './photo.component.html',
  styleUrls: ['./photo.component.css']
})
export class PhotoComponent implements OnInit {

  newPhoto : Photo;
  photographe: Photographe;
  description : string;
  image : string;
  titre : string;
  tagListe : Tag[];
   
      

  constructor(private formBuilder: FormBuilder,
    private photoService: PhotoService,
    private router: Router,
    private httpClient: HttpClient,) {
     }

  ngOnInit() {
  }

}
