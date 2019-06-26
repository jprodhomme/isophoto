import { Injectable } from '@angular/core';
import { Photographe } from '../model/photographe.model';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PhotographeService {
  pseudoCommentaire: Observable<string>;
  newPhoto : Photographe;


  availablePhotographes: Photographe[];

  availablePseudo : string[];

  availablePhotographe$: BehaviorSubject<Photographe[]> = new BehaviorSubject(this.availablePhotographes);

  availablePseudo$: BehaviorSubject<string[]> = new BehaviorSubject(this.availablePseudo);

  constructor(private httpClient: HttpClient) { }

    /**
   * @param newPhotographe le nouveau photographe à créer
   */
  public addPhotographe(newPhotographe: Photographe) {
    this.httpClient.post<Photographe>('POST',environment.apiUrl +'sign-up' + newPhotographe, ).subscribe(
      newPhotographe => {
       this.availablePhotographes.push(newPhotographe);
      }
    );
  }

  public getPseudo(): Observable<string[]> {
    return this.httpClient.get<string[]>(environment.apiUrl + 'pseudos');
  }

  public publishPseudos(){
    this.getPseudo().subscribe(
      pseudoList =>{
        this.availablePseudo = pseudoList;
        this.availablePseudo$.next(this.availablePseudo);
        console.log(this.availablePseudo.length);
      }

    )
  }

}

  
