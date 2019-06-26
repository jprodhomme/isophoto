import { Injectable } from '@angular/core';
import { Don } from '../model/don.model';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class DonService {

  constructor(private httpClient: HttpClient, private router : Router) { }

 

  addDon(photoId: number) {
    this.httpClient.put<Don>(environment.apiUrl + 'adddon/' + photoId, null).subscribe(
      newDon => {
        this.router.navigate(['/']);
        
      },
      error => console.log('Error while signUp'));
  }

}
