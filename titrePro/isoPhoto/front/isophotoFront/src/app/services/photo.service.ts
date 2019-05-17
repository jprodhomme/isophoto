import { Injectable } from '@angular/core';
import { Observable, of, BehaviorSubject } from 'rxjs';
import { map }  from 'rxjs/operators';
import { Photo } from '../model/photo.model';
import { Tag } from '../model/tag.model';
import { Photographe } from '../model/photographe.model';
import { Authority } from '../model/authority.model';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class PhotoService{


  availablePhotos: Photo[];

  availablePhotos$: BehaviorSubject<Photo[]> = new BehaviorSubject(this.availablePhotos);

  constructor(private httpClient: HttpClient) { }

  /*
   * La fonction getAllItem() est privée car elle n'a besoin d'être appellée que dans le service.
   */
  public getAllPhotos(): Observable<Photo[]> {
    console.log('getAllPhotos' + this.availablePhotos$)
    return this.httpClient.get<Photo[]>('http://localhost:8080/api/photos');
  }

  /*
   * La fonction publishItems() est chargée une fois au démarrage de l'application.
   * Elle récupère la liste des Items depuis la base de données et met à jour la liste et la liste observable.
   */
  public publishPhotos() {
    this.getAllPhotos().subscribe(
      PhotoList => {
        this.availablePhotos= PhotoList;
        this.availablePhotos$.next(this.availablePhotos);
      });
  }

  /**
   * @param photoId l'id qu'il faut rechercher dans la liste.
   */
  public findItem(photoId: number): Observable<Photo> {
    if (photoId) {
      if (!this.availablePhotos) {
        return this.getAllPhotos().pipe(map(photos => photos.find(photo => photo.idPhoto === photoId)));
      }
      return of(this.availablePhotos.find(photo => photo.idPhoto === photoId));
    } else {
      return of(new Photo(0, "", "", "", Tag[""], new Photographe(0, "", "", "", "", "", new Authority(0, ""))));
    }
  }
  /*
   * @param newItem
   */
  public createPhoot(newPhoto: Photo) {
    this.httpClient.post<Photo>('http://localhost:8080/addphoto', newPhoto).subscribe(
      newPhoto => {
        this.availablePhotos.push(newPhoto);
        this.availablePhotos$.next(this.availablePhotos);
      }
    )
  }

}
