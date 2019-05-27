import { Component, } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import {LoginService} from '../service/login.service';
import { Photographe } from '../model/photographe.model';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition} from '@angular/material';


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


  horizontalPostion : MatSnackBarHorizontalPosition = 'left';
  verticalPosition : MatSnackBarVerticalPosition = 'bottom';

  constructor(private fb: FormBuilder,
              private snackBar : MatSnackBar,
              private loginService: LoginService) {}

  onSubmit() {
    const photographe = new Photographe();
   
    photographe.pseudo = this.loginForm.value.pseudo;
    photographe.password = this.loginForm.value.password;


    

    this.loginService.signIn(photographe);
   
   
      this.snackBar.open('Bonjour ' + photographe.pseudo , undefined, {
        horizontalPosition : 'left',
        verticalPosition : 'bottom',
        panelClass : ['snackBar', 'snackBar2'] 
  
        
      });
      return photographe.pseudo;
    
  }
}

