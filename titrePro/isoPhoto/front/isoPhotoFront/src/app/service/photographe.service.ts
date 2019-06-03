import { Injectable } from '@angular/core';
import { Photographe } from '../model/photographe.model';
import { BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PhotographeService {

  newPhoto : Photographe;

  availablePhotographes: Photographe[];

  availablePhotographe$: BehaviorSubject<Photographe[]> = new BehaviorSubject(this.availablePhotographes);

  constructor(private httpClient: HttpClient) { }

    /**
   * @param newPhotographe le nouveau friend à créere
   */
  public addPhotographe(newPhotographe: Photographe) {
    this.httpClient.post<Photographe>('POST','http://localhost:8080/api/sign-up' + newPhotographe, ).subscribe(
      newPhotographe => {
       this.availablePhotographes.push(newPhotographe);
      }
    );
}
}
