import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DonComponent } from './don/don.component';
import { PhotoComponent } from './photo/photo.component';
import { UploadComponent } from './upload/upload.component';
import { AddPhotoComponent } from './add-photo/add-photo.component';
import { SignupComponent } from './signup/signup.component';
import { SigninComponent } from './signin/signin.component';
// import { HeaderComponent } from './header/header.component';

const routes: Routes = [

  {path : 'profil', component : DonComponent},
  {path : 'photos', component : AddPhotoComponent},
  {path : 'upload', component : UploadComponent},
  {path : 'signup', component : SignupComponent},
  {path : 'signin', component : SigninComponent},



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
exports: [RouterModule]
})
export class AppRoutingModule { }
