import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DonComponent } from './don/don.component';
import { UploadComponent } from './upload/upload.component';
import { SignupComponent } from './signup/signup.component';
import { SigninComponent } from './signin/signin.component';
import { PhotoListComponent } from './photo-list/photo-list.component';
import { PhotoDetailsComponent } from './photo-details/photo-details.component';

const routes: Routes = [



  {path : 'dons', component : DonComponent},
  {path : 'photos', component : PhotoListComponent},
  {path : 'upload', component : UploadComponent},
  {path : 'signup', component : SignupComponent},
  {path : 'signin', component : SigninComponent},
  {path : 'photodetails/:photoId', component : PhotoDetailsComponent},



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
exports: [RouterModule]
})
export class AppRoutingModule { }
