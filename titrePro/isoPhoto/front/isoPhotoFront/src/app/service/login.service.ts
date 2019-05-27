import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Photographe } from '../model/photographe.model';

import {JsonWebToken} from '../model/jwt.model';
import {Router} from '@angular/router';
import {environment} from '../../environments/environment';
import * as jwt_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  //garde l'authority pour l'utiliser dans les guards
  userRoles: BehaviorSubject<string> = new BehaviorSubject('');

  constructor(private httpClient: HttpClient, private router: Router) {
    this.getUserRoles();
  }

  public get loggedIn(): boolean {
    return sessionStorage.getItem(environment.accessToken) !== null;
  }

  signIn(photographe: Photographe) {
    sessionStorage.removeItem(environment.accessToken);
    this.httpClient.post<JsonWebToken>(environment.apiUrl + 'sign-in', photographe).subscribe(
      token => {
        sessionStorage.setItem(environment.accessToken, token.token);
        console.log('Token =>' , token.token)
        this.getUserRoles();
        this.router.navigate(['/']);
        
        
      },
      error => console.log('Error while login'));
  }
  signUp(photographe: Photographe) {
    this.httpClient.post<Photographe>(environment.apiUrl + 'sign-up', photographe).subscribe(
      newPhotographe => {
        
        
      },
      error => console.log('Error while signUp'));
  }

  public signOut() {
   
    sessionStorage.removeItem(environment.accessToken);
    this.router.navigate(['']);
  }

  private getUserRoles() {
    if (sessionStorage.getItem(environment.accessToken)) {
      const decodedToken = jwt_decode(sessionStorage.getItem(environment.accessToken));
   
      const authorities: string = decodedToken.auth;
      this.userRoles.next(authorities);
    }
  }
}