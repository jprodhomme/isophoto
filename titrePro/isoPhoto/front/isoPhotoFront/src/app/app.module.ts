import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; 
import { MaterialModule } from './material-module';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PhotoComponent } from './photo/photo.component';
import { PhotographeComponent } from './photographe/photographe.component';
import { DonComponent } from './don/don.component';
import { CommentaireComponent } from './commentaire/commentaire.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HttpClientModule } from '@angular/common/http';
import { UploadComponent } from './upload/upload.component';
import { AddPhotoComponent } from './add-photo/add-photo.component';
import { FormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    AppComponent,
    PhotographeComponent,
    DonComponent,
    CommentaireComponent,
    HeaderComponent,
    PhotoComponent,
    FooterComponent,
    UploadComponent,
    AddPhotoComponent,
    
  ],
  imports: [
 

    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
     FormsModule,
  
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
