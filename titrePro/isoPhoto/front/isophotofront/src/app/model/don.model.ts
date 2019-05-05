import { Photo } from './photo.model';

export class Don {


    private id : number;
    private commentaire : string;
    private dateDon : Date;
    private montant : number;
    private photo : Photo;

    constructor(id : number,
                commentaire : string,
                dateDon : Date,
                montant : number,
                photo : Photo){
                    
                    this.id = id;
                    this.commentaire = commentaire;
                    this.dateDon = dateDon;
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

    public getDateDon(): Date {
        return this.dateDon;
    }

    public setDateDon(dateDon: Date): void {
        this.dateDon = dateDon;
    }

    public getMontant(): number {
        return this.montant;
    }

    public setMontant(montant: number): void {
        this.montant = montant;
    }

    public getPhoto(): Photo {
        return this.photo;
    }

    public setPhoto(photo: Photo): void {
        this.photo = photo;
    }


}