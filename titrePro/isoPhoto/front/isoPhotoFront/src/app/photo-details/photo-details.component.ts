import { Component, OnInit, Inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Photo } from '../model/photo.model';
import { PhotoService } from '../service/photo.service';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { DOCUMENT } from '@angular/common'
import * as jwt_decode from 'jwt-decode';
import { CommentaireService } from '../service/commentaire.service';
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
  stringCommentaire: Observable<string[]>;


  commentaireBox: string = "";


  constructor(private route: ActivatedRoute,
    private photoService: PhotoService,
    private commentaireService: CommentaireService,
    private router: Router,
    private httpClient: HttpClient,
    @Inject(DOCUMENT) document) { }

  ngOnInit() {

    this.photoId = +this.route.snapshot.params.photoId;
    console.log(this.photoId);

    this.httpClient.get<Photo>(environment.apiUrl + 'photosbyid/' + this.photoId).subscribe(
      (result) => {
        this.photoImage = result.image;
        console.log("image " + this.photoImage);
        this.photoTitre = result.titre;
        console.log("titre " + this.photoTitre);
        this.photoDesc = result.description;
        console.log("desc " + this.photoDesc);
      });

    const decodedToken = jwt_decode(sessionStorage.getItem(environment.accessToken));

    this.photoPseudo = decodedToken.pseudo;


    this.commentaireService.getCommentaire(this.photoId);
    
    this.stringCommentaire = this.commentaireService.stringCommentaire;

    this.stringCommentaire.subscribe(
      (result)=>{

      }
    );
  }

  postCommentaire() {

    this.commentaireService.addCommentaire(this.photoId, this.commentaireBox);
    this.commentaireBox = "";
    setTimeout(() => this.stringCommentaire = this.commentaireService.stringCommentaire, 100);
    this.stringCommentaire.subscribe(
      (result)=>{

        console.log(result);
      }
    );
  }


}

