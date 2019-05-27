import { Component, OnInit } from '@angular/core';
import { Photographe } from '../model/photographe.model';
import { PhotographeService } from '../service/photographe.service';
import { FormBuilder, Validators } from '@angular/forms';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  loginForm = this.fb.group({
    pseudo: [null, Validators.required],
    nom: [null, Validators.required],
    prenom: [null, Validators.required],
    email: [null, Validators.required],
    password: [null, Validators.compose([
      Validators.required, Validators.minLength(5), Validators.maxLength(255)])
    ]
  });
  
  newPhotographe : Photographe;

  constructor(private fb : FormBuilder,
              private photographeService : PhotographeService, 
              private loginService : LoginService) { }

  ngOnInit() {
    this.newPhotographe = new Photographe(0, '','','', '', '', null);
  }

  addPhotographe(){
    this.photographeService.addPhotographe(this.newPhotographe)
  }

  onSubmit() {
    const photographe = new Photographe();
    photographe.prenom = this.loginForm.value.prenom;
    photographe.nom = this.loginForm.value.nom;
    photographe.pseudo = this.loginForm.value.pseudo;
    photographe.email = this.loginForm.value.email;
    photographe.password = this.loginForm.value.password;
    this.loginService.signUp(photographe);
    console.log(photographe.pseudo);
  }
}
