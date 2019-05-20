import { Photo } from './photo.model';

export class Commentaire{

    private id : number;
    private commentaire : string;
    private photo : Photo;

    constructor(id : number,
                commentaire : string,
                photo : Photo){

                   this.id = id;
                   this.commentaire = commentaire;
                   this.photo = photo;
                }

    public getId(): number {
        return this.id;
    }

    public setId(id: number): void {
        this.id = id;
    }

    public getCommentaire(): string {
        return this.commentaire;
    }

    public setCommentaire(commentaire: string): void {
        this.commentaire = commentaire;
    }

    public getPhoto(): Photo {
        return this.photo;
    }

    public setPhoto(photo: Photo): void {
        this.photo = photo;
    }




}