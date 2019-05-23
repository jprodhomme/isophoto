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
  newPhotographe = new Photographe(0, '', '' ,'', '', '', null);

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
    this.newPhoto = new Photo(0, '','','',[],[],[], this.newPhotographe);
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



  // uploadForm = this.fb.group({
  //   titre: [null, Validators.required],
  //   description: [null, Validators.required],
  //   photographe: [null, Validators.required],
  //   tag: [null, Validators.required],
   
  // });
  
 
  // newPhotographe = new Photographe();

  // photoList : Photo[] = []
  // tagList : Tag[]= []
  // arrayTag : string;
  // formulaire: string;
  // selectedFiles: FileList;
  // currentFileUpload: File;
  // uploadService: UploadFileService;
  
 

  // constructor(private photoService : PhotoService, private fb : FormBuilder) { }

  
  
  
  // selectFile(event) {
    
  //   this.selectedFiles = event.target.files;
  // }

  // addPhoto(){

   
  //   const photo = new Photo();
  //   photo.titre = this.uploadForm.value.prenom;
  //   photo.description = this.uploadForm.value.nom;
  //   photo.tagListe = this.uploadForm.value.pseudo;
  //   photo.image = this.uploadForm.value.image;
    

  //   this.photoService.addPhoto(photo, this.arrayTag)
    
  //   this.currentFileUpload = this.selectedFiles.item(0);
  //   this.photoService.pushFileToStorage(this.currentFileUpload).subscribe(event => {
  //     if (event instanceof HttpResponse) {
  //       console.log('File is completely uploaded!');
  //     }
  //   });
  //   this.selectedFiles = undefined;
  // }



}

