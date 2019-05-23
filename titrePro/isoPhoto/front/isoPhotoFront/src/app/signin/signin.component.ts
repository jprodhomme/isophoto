import { Component, } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import {LoginService} from '../service/login.service';
import { Photographe } from '../model/photographe.model';


@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent {

  loginForm = this.fb.group({
    pseudo: [null, Validators.required],
    password: [null, Validators.compose([
      Validators.required, Validators.minLength(5), Validators.maxLength(255)])
    ]
  });

  constructor(private fb: FormBuilder, 
              private loginService: LoginService) {}

  onSubmit() {
    const photographe = new Photographe();
   
    photographe.pseudo = this.loginForm.value.pseudo;
    photographe.password = this.loginForm.value.password;

    this.loginService.signIn(photographe);

    
  }

}
