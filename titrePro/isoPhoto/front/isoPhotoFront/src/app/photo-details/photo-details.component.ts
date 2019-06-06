import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Photo } from '../model/photo.model';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import * as jwt_decode from 'jwt-decode';
import { CommentaireService } from '../service/commentaire.service';
import { Commentaire } from '../model/commentaire.model';
import { Photographe } from '../model/photographe.model';
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

  stringCommentaire: Observable<Commentaire[]>;

  pseudoPhoto: string;

  comList: Commentaire[];

  commentaireBox: string = "";

  loggedPhotographe: string;


  photographeCom: Observable<Photographe>;

  constructor(private route: ActivatedRoute,
    private commentaireService: CommentaireService,
    private httpClient: HttpClient) { }

  ngOnInit() {
    const decodedToken = jwt_decode(sessionStorage.getItem(environment.accessToken));
    this.loggedPhotographe = decodedToken.sub;

    this.photoId = +this.route.snapshot.params.photoId;

    this.httpClient.get<Photo>(environment.apiUrl + 'photosbyid/' + this.photoId).subscribe(
      (result) => {
        this.photoImage = result.image;
        this.photoTitre = result.titre;
        this.photoDesc = result.description;
        this.comList = result.commentaires;
      }
    );

    this.httpClient.get<Photographe>(environment.apiUrl + 'photographefromphotoid/' + this.photoId).subscribe(
      (resultat) => {
        this.pseudoPhoto = resultat.pseudo
      }
    );

    this.commentaireService.getCommentaire(this.photoId);
    this.stringCommentaire = this.commentaireService.stringCommentaire;
    this.stringCommentaire.subscribe();


  }

  postCommentaire() {

    this.commentaireService.addCommentaire(this.photoId, this.loggedPhotographe, this.commentaireBox);
    this.commentaireBox = "";

    setTimeout(() => this.stringCommentaire = this.commentaireService.stringCommentaire, 100);
    this.stringCommentaire.subscribe();


  }

  deleteCommentaire(comId) {

    this.commentaireService.deleteCommentaire(this.photoId, this.loggedPhotographe, comId);

    setTimeout(() => this.stringCommentaire = this.commentaireService.stringCommentaire, 100);
    this.stringCommentaire.subscribe();

  }


}

