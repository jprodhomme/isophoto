import { Component, OnInit } from '@angular/core';
import { PhotoService } from '../service/photo.service';
import { Photo } from '../model/photo.model';
import { Photographe } from '../model/photographe.model';
import { Tag } from '../model/tag.model';
import { Authority } from '../model/authority.model';
import { UploadFileService } from '../service/upload-file.service';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-add-photo',
  templateUrl: './add-photo.component.html',
  styleUrls: ['./add-photo.component.css']
})
export class AddPhotoComponent implements OnInit {
    
  newPhoto : Photo;
  newPhotographe = new Photographe(1, 'Picard', 'Laurent' ,'lolo', 'lolo@isophoto.fr', 'password123', null) 
  // newTag : Tag[] = new Tag(45, 'testTagDeMerde')
  // newTag = new Tag(0,'')

  photoList : Photo[] = []
  tagList : Tag[]= []
  arrayTag : string;
  formulaire: string;
  selectedFiles: FileList;
  currentFileUpload: File;
  uploadService: UploadFileService;
  
 

  constructor(private photoService : PhotoService,
               ) { }

  ngOnInit() {
    this.newPhoto = new Photo('','','',[], this.newPhotographe);
  }
  selectFile(event) {
    
    this.selectedFiles = event.target.files;
  }

  addPhoto(){

   
    this.photoService.addPhoto(this.newPhoto, this.arrayTag)
    
    this.currentFileUpload = this.selectedFiles.item(0);
    this.photoService.pushFileToStorage(this.currentFileUpload).subscribe(event => {
      if (event instanceof HttpResponse) {
        console.log('File is completely uploaded!');
      }
    });
    this.selectedFiles = undefined;
  }


}
