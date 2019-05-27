import { Component, OnInit } from '@angular/core';
import { LoginService } from '../service/login.service';
import { SigninComponent } from '../signin/signin.component';


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

constructor(private loginService : LoginService) { }

ngOnInit() {

  sessionStorage.clear;
  this.getConnection();

  }

getConnection(){

  this.loginService.userRole.subscribe(userRole => {

    this.isAdmin = userRole.includes("admin");
    this.isPhotographe = userRole.includes('photographe');
    this.isLogged = userRole.length > 0;
    this.photographePseudo = userRole.sub();

  });
}

logout(){

  this.loginService.signOut();
  this.isLogged =false;
  this.isPhotographe = false;
  this.isAdmin = false;
}


     
}
