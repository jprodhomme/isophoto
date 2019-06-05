import { Component } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { UploadFileService } from '../service/upload-file.service';
import { Photo } from '../model/photo.model';
import { PhotoService } from '../service/photo.service';
import { Tag } from '../model/tag.model';
import { environment } from 'src/environments/environment';
import * as jwt_decode from 'jwt-decode';
import { Validators, FormBuilder } from '@angular/forms';
import { Photographe } from '../model/photographe.model';


@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css'],
  providers: [UploadFileService]
})

export class UploadComponent {
  photographe = new Photographe();
  photo=new Photo();
  newPhoto: Photo;
  photoList: Photo[] = []
  tagList: Tag[] = []
  tagString: string;
  formulaire: string;

  
  selectedFiles: FileList;
  currentFileUpload: File;
  uploadService: UploadFileService;

  image :string;


  uploadForm = this.fb.group({
    titre: [null, Validators.required],
    description: [null, Validators.required],
    tagListe: [null, Validators.required],
    image: [null, Validators.required],
    
  });



  constructor(private photoService: PhotoService, private fb : FormBuilder ) { }

  ngOnInit() {

    this.newPhoto = new Photo(0, '', '', '', [], [], [], this.getPhotographePseudo());

    
    this.image = localStorage.getItem('image');
    console.log("THIS IMAGE ====>" + this.image);
  }
  selectFile(event) {

    this.selectedFiles = event.target.files;
  }

  getPhotographePseudo() {
    if (sessionStorage.getItem(environment.accessToken)) {
      const decodedToken = jwt_decode(sessionStorage.getItem(environment.accessToken));
      console.log(decodedToken.sub);
      return decodedToken.sub;
    }
  }



  onSubmit(){

    let photographe = new Photographe();
    
    let photo =new Photo();

    photographe.pseudo = this.getPhotographePseudo();
    photo.image = this.split(this.uploadForm.value.image);
    localStorage.setItem('image', photographe.pseudo+"-"+photo.image); //pour l'afficher sous le formulaire d'upload
        
    photo.titre = this.uploadForm.value.titre;
    photo.description = this.uploadForm.value.description;
    photo.image = this.split(this.uploadForm.value.image);
  
    this.photoService.addPhoto(photo, this.uploadForm.value.tagListe, photographe.pseudo);
    this.currentFileUpload = this.selectedFiles.item(0);

    
   
    this.photoService.pushFileToStorage(this.currentFileUpload, photographe.pseudo).subscribe(event => {
      if (event instanceof HttpResponse) {
        console.log('File is completely uploaded!');
      }
    });
    this.selectedFiles = undefined;
  }

  split(pathImage : string){

    let split;
    if (pathImage.indexOf('/') > -1){
      split = pathImage.split('/');
    } else {
      split = pathImage.split('\\');
    } return split[split.length -1];
  }

}