import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { LoginService } from '../service/login.service';
import { environment } from 'src/environments/environment';
import * as jwt_decode from 'jwt-decode';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isLogged = false;
  isAdmin : boolean;
  isPhotographe : boolean;
  photographePseudo : string;
  pseudo: string;
  thisIsPseudo: string;

  constructor(private loginService : LoginService) { }

  ngOnInit() {

    sessionStorage.clear;
    this.getConnection();
    
    
    const decodedToken = jwt_decode(sessionStorage.getItem(environment.accessToken));
    this.thisIsPseudo = decodedToken.pseudo;
    this.pseudo= decodedToken.pseudo;
    console.log(this.pseudo)

  }

  getConnection(){

    this.loginService.userRole.subscribe(userRole => {
    this.isAdmin = userRole.includes("admin");
    this.isPhotographe = userRole.includes('photographe');
    this.isLogged = userRole.length > 0;
    this.photographePseudo = userRole.sub();

    sessionStorage.setItem('pseudo', this.photographePseudo);

  });
}

logout(){

  this.loginService.signOut();
  this.isLogged =false;
  this.isPhotographe = false;
  this.isAdmin = false;
  this.thisIsPseudo = "";
}

public clearStorage(){
  window.localStorage.clear();

  }

  
  classToggle() {
    const navs = document.querySelectorAll('.Navbar__Items')
    
    navs.forEach(nav => nav.classList.toggle('Navbar__ToggleShow'));
  }
  
 

}
