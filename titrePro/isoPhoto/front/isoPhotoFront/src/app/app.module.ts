import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; 
import { MaterialModule } from './material-module';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PhotoComponent } from './photo/photo.component';
import { PhotographeComponent } from './photographe/photographe.component';
import { DonComponent } from './don/don.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { UploadComponent } from './upload/upload.component';
import { PhotoListComponent} from './photo-list/photo-list.component';
import { FormsModule } from '@angular/forms';
import { SignupComponent } from './signup/signup.component';
import { SigninComponent } from './signin/signin.component';
import {ReactiveFormsModule} from '@angular/forms';
import {PhotographeGuard} from './guards/photographe.guard';
import {AdminGuard} from './guards/admin.guard';
import {JwtInterceptor} from './http-interceptor/jwt.interceptor';
import { PhotoDetailsComponent } from './photo-details/photo-details.component';
// import { GalleryModule } from '@gnx-gallery/core';
// import { LightboxModule } from '@ngx-gallery/lightbox';
// import { GallerizeModule } from 'ngx-gallery/gallerize';


@NgModule({
  declarations: [
    AppComponent,
    PhotographeComponent,
    DonComponent,
    HeaderComponent,
    PhotoComponent,
    PhotoListComponent,
    FooterComponent,
    UploadComponent,
    PhotoComponent,
    SignupComponent,
    SigninComponent,
    PhotoDetailsComponent,
    
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    // GalleryModule,
    // LightboxModule,
    // GallerizeModule,
    ],
    providers: [ PhotographeGuard, AdminGuard,
      {
        provide: HTTP_INTERCEPTORS,
        useClass: JwtInterceptor,
        multi: true
      }],
  bootstrap: [AppComponent]
})
export class AppModule { }
