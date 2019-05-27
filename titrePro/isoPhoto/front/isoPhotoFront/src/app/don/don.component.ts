import { Component, OnInit } from '@angular/core';
import * as jwt_decode from 'jwt-decode';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-don',
  templateUrl: './don.component.html',
  styleUrls: ['./don.component.css']
})
export class DonComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }


testDecodeJwt(){
  if (sessionStorage.getItem(environment.accessToken)){
    const decodedToken =  jwt_decode(sessionStorage.getItem(environment.accessToken));
    console.log(decodedToken.sub);
    return decodedToken.sub;
  }
}
}
