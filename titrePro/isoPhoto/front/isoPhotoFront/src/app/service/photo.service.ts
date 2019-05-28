import { Injectable } from '@angular/core';
import { Photo } from '../model/photo.model';
import { HttpClient, HttpRequest, HttpEvent } from '@angular/common/http';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PhotoService {
  
  newPhoto : Photo;

 public availablePhotos: Photo[];

  // la liste observable que l'on rend visible partout dans l'appli
  availablePhotos$: BehaviorSubject<Photo[]> = new BehaviorSubject(this.availablePhotos);

  constructor(private httpClient: HttpClient) { }

  getPhotos(): Observable<Photo[]>{
    return this.httpClient.get<Photo[]>(environment.apiUrl + 'photos');
  }
  /**
   * @param newPhoto nouvelle photo à créer
   */
  public addPhoto(newphoto: Photo, arrayTag : string, pseudo : string) {
    console.log(pseudo);
    this.httpClient.post<Photo>('http://localhost:8080/api/create/' + arrayTag + '/' + pseudo, newphoto).subscribe(
      newPhoto => {

      }
    );

  }
  /**
   * La fonction getAllPhotos() est privée car elle n'a besoin d'être appellée que dans le service.
   */
  public getAllPhotos(): Observable<Photo[]> {
    console.log('getAllPhotos' + this.availablePhotos)
    return this.httpClient.get<Photo[]>('http://localhost:8080/api/photos');
  }

 
  pushFileToStorage(file: File): Observable<HttpEvent<{}>> {

    const formdata: FormData = new FormData();

    formdata.append('file', file);

    const req = new HttpRequest('POST', 'http://localhost:8080/api/photo/uploadphoto', formdata, {

      reportProgress: true,
      responseType: 'text'  
      
    }
    );
    return this.httpClient.request(req);
  }


  public findPhotoByPhotographe(pseudo: string) : Observable<Photo[]>{
    
    
    return this.httpClient.get<Photo[]>(environment.apiUrl + 'photosbyphotographe/' + pseudo);
   }
    
  }

