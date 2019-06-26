import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; 
import { MaterialModule } from './material-module';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PhotoComponent } from './photo/photo.component';
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
import { MatToolbarModule, MatSidenavModule, MatListModule, MatButtonModule, MatIconModule } from "@angular/material";
import { FlexLayoutModule } from "@angular/flex-layout";
import { NgxPayPalModule } from 'ngx-paypal';
import { SidenavListComponent } from './sidenav-list/sidenav-list.component';
import { DonComponent } from './don/don.component';


@NgModule({
  declarations: [
    AppComponent,
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
    SidenavListComponent,
    
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    MatToolbarModule,
    MatSidenavModule,
    MatListModule,
    MatIconModule,
    MatButtonModule,
    NgxPayPalModule,
    FlexLayoutModule,
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
