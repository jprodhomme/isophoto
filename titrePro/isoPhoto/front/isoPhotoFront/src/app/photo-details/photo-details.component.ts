import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Photo } from '../model/photo.model';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import * as jwt_decode from 'jwt-decode';
import { CommentaireService } from '../service/commentaire.service';
import { Commentaire } from '../model/commentaire.model';
@Component({
  selector: 'app-photo-details',
  templateUrl: './photo-details.component.html',
  styleUrls: ['./photo-details.component.css']
})

export class PhotoDetailsComponent implements OnInit {
  photoId: number;
  photoImage: string;
  photoTitre: string;
  photoDesc: string;
  photoPseudo: string;

  stringCommentaire: Observable<Commentaire[]>;

  comList : Commentaire[];

  commentaireBox: string = "";

  constructor(private route: ActivatedRoute,
              private commentaireService: CommentaireService,
              private httpClient: HttpClient,) { }

  ngOnInit() {
    
    this.photoId = +this.route.snapshot.params.photoId;
   
    this.httpClient.get<Photo>(environment.apiUrl + 'photosbyid/' + this.photoId).subscribe(
      (result) => {
        this.photoImage = result.image;
        this.photoTitre = result.titre;
        this.photoDesc = result.description;
        this.comList = result.commentaires;
      }
    ); 

    const decodedToken = jwt_decode(sessionStorage.getItem(environment.accessToken));

    this.photoPseudo = decodedToken.pseudo; 
    this.commentaireService.getCommentaire(this.photoId);    
    this.stringCommentaire = this.commentaireService.stringCommentaire;
    this.stringCommentaire.subscribe();
  }

  postCommentaire() {

    this.commentaireService.addCommentaire(this.photoId, this.photoPseudo, this.commentaireBox);
    this.commentaireBox = "";

    setTimeout(() => this.stringCommentaire = this.commentaireService.stringCommentaire, 100);
    this.stringCommentaire.subscribe();
  }

  deleteCommentaire(comId){
   
    this.commentaireService.deleteCommentaire(this.photoId, this.photoPseudo, comId );
    
    setTimeout(() => this.stringCommentaire = this.commentaireService.stringCommentaire, 100);
    this.stringCommentaire.subscribe();
    
  }

}


         

        
        
        
       