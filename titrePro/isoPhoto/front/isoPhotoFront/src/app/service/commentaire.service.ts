import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Commentaire } from '../model/commentaire.model';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CommentaireService {

  public stringCommentaire: Observable<Commentaire[]>;

  constructor(private httpClient: HttpClient) { }

  public availableCommentaires: Commentaire[] = [];

  
  public getCommentaire(photoId: number) {

    this.stringCommentaire = this.httpClient.get<Commentaire[]>(environment.apiUrl + 'photocommentairesbyid/' + photoId);

  }

  public addCommentaire(idPhoto: number, commentaireBox: string, pseudo :string) {

    this.httpClient.put<string>(environment.apiUrl + 'addcommentaire/' + idPhoto + '/'+ commentaireBox  +'/' + pseudo, null).subscribe();
    
    this.stringCommentaire = this.httpClient.get<Commentaire[]>(environment.apiUrl + 'photocommentairesbyid/' + idPhoto);

  }

  public deleteCommentaire(idPhoto : number, pseudo : string, comId: number){

    this.httpClient.delete<string>(environment.apiUrl + 'deletecommentaire/' + comId + '/'+ pseudo).subscribe();

    this.stringCommentaire = this.httpClient.get<Commentaire[]>(environment.apiUrl + 'photocommentairesbyid/' + idPhoto);

  }

  
}
