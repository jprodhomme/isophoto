import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Commentaire } from '../model/commentaire.model';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CommentaireService {

  stringCommentaire : Observable<string[]>;

  constructor(private httpClient: HttpClient) { }


  public availableCommentaires: Commentaire[] = [];

  // la liste observable que l'on rend visible partout dans l'appli
  public availableCommentaires$ = new BehaviorSubject(this.availableCommentaires);


public getCommentaire(photoId : number){


  this.stringCommentaire = this.httpClient.get<string[]>(environment.apiUrl + 'photocommentairesbyid/' + photoId);   
          
}    


  public addCommentaire(idPhoto : number, commentaireBox :string){
   
    this.httpClient.put<string>(environment.apiUrl + 'addcommentaire/' + idPhoto + '/' + commentaireBox, null).subscribe();
    console.log("1")
    this.stringCommentaire = this.httpClient.get<string[]>(environment.apiUrl + 'photocommentairesbyid/' + idPhoto);   
          
  }
}
