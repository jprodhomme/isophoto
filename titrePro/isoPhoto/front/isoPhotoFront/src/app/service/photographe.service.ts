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

  availablePhotographe$: BehaviorSubject<Photographe[]> = new BehaviorSubject(this.availablePhotographes);

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

  
}
