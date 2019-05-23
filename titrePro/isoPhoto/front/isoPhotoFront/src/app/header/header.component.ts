import { Component, OnInit } from '@angular/core';
import { LoginService } from '../service/login.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  

  constructor(private loginService : LoginService) { }

  ngOnInit() {
  }
  

  logout(){
  this.loginService.signOut()
  console.log(environment.accessToken)}
}
