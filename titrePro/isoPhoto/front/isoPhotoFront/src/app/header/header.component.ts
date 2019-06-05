import { Component, OnInit } from '@angular/core';
import { LoginService } from '../service/login.service';
import { environment } from 'src/environments/environment.prod';


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

  constructor(private loginService : LoginService) { }

  ngOnInit() {

    sessionStorage.clear;
    this.getConnection();
    this.pseudo = localStorage.getItem('pseudo');

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
}

public clearStorage(){
  window.localStorage.clear();

  }
     
}
