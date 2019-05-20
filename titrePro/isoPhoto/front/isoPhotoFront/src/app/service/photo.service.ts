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

  availablePhotos: Photo[];

  // la liste observable que l'on rend visible partout dans l'appli
  availablePhotos$: BehaviorSubject<Photo[]> = new BehaviorSubject(this.availablePhotos);

  constructor(private httpClient: HttpClient) { }

  /**
   * @param newFriend le nouveau friend à créer
   */
  public addPhoto(newphoto: Photo, arrayTag : string) {
    this.httpClient.post<Photo>('http://localhost:8080/api/create/' + arrayTag, newphoto).subscribe(
      newPhoto => {
        // this.availablePhotos.push(newphoto);
        console.log(newPhoto);
      }
    );

    console.log(newphoto.getTagListe().length)
  }
  /**
   * La fonction getAllFriend() est privée car elle n'a besoin d'être appellée que dans le service.
   */
  public getAllPhotos(): Observable<Photo[]> {
    console.log('getAllPhotos' + this.availablePhotos)
    return this.httpClient.get<Photo[]>('http://localhost:8080/api/photos');
  }

  /**
   * @param idPhoto l'id qu'il faut rechercher dans la liste.
   */
  public findPhotoById(idPhoto: number): Observable<Photo> {
    if (idPhoto) {
      if (!this.availablePhotos) {
        return this.getAllPhotos().pipe(map(photos => photos.find(photo => photo.idPhoto === idPhoto)));
      }
      return of(this.availablePhotos.find(photo => photo.idPhoto === idPhoto));
    } else {
      return ; // TO DO 
    }
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
   
  
}
