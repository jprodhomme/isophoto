import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../service/login.service';
import { environment } from 'src/environments/environment';
import * as jwt_decode from 'jwt-decode';


@Component({
  selector: 'app-sidenav-list',
  templateUrl: './sidenav-list.component.html',
  styleUrls: ['./sidenav-list.component.css']
})
export class SidenavListComponent implements OnInit {
  @Output() sidenavClose = new EventEmitter();
  
  isLogged = false;
  isAdmin : boolean;
  isPhotographe : boolean;
  photographePseudo : string;
  pseudo: string;
  thisIsPseudo: string;

  public onSidenavClose = () => {
    this.sidenavClose.emit();
  }

  constructor(private router : Router,
              private loginService : LoginService) { }

  ngOnInit() {

    console.log('init lolo')


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


  public onAction(nameRouting: string){
    this.router.navigate([nameRouting]);
    this.onSidenavClose();
  }


public onHome(){
  this.router.navigate(['']);
}

}
