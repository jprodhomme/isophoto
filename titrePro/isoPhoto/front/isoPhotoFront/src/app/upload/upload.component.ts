
import { Component } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { UploadFileService } from '../service/upload-file.service';
import { Photo } from '../model/photo.model';
import { PhotoService } from '../service/photo.service';
import { Photographe } from '../model/photographe.model';
import { Tag } from '../model/tag.model';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css'],
  providers: [UploadFileService]
})

export class UploadComponent {
  
  newPhoto : Photo;
  newPhotographe = new Photographe(0, '', '' ,'', '', '', null) 

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

