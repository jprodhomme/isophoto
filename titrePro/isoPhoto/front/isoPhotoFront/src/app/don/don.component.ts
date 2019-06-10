import { Component, OnInit } from '@angular/core';
import * as jwt_decode from 'jwt-decode';
import { environment } from 'src/environments/environment';
import {
  IPayPalConfig,
  ICreateOrderRequest 
} from 'ngx-paypal';

declare let paypal: any;

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

addScript: boolean = false;
paypalLoad: boolean = true;

finalAmount: number = 1;

paypalConfig = {
  env: 'sandbox',
  client: {
    sandbox: '3PH2AE2H92PMSM2V',
    production: 'AESwYdwIUh1Nyt6KaZSvO4H9FtUcAQCHSxrjq7umPQ8.Sip0bBIPScMS'
  },
  commit: true,
  payment: (data, actions) => {
    return actions.payment.create({
      payment: {
        transactions: [
          { amount: { total: this.finalAmount, currency: 'INR' } }
        ]
      }
    });
  },
  onAuthorize: (data, actions) => {
    return actions.payment.execute().then((payment) => {
      //Do something when payment is successful.
    })
  }
};

ngAfterViewChecked(): void {
  if (!this.addScript) {
    this.addPaypalScript().then(() => {
      paypal.Button.render(this.paypalConfig, '#paypal-checkout-btn');
      this.paypalLoad = false;
    })
  }
}

addPaypalScript() {
  this.addScript = true;
  return new Promise((resolve, reject) => {
    let scripttagElement = document.createElement('script');    
    scripttagElement.src = 'https://www.paypalobjects.com/api/checkout.js';
    scripttagElement.onload = resolve;
    document.body.appendChild(scripttagElement);
  })
}
}
