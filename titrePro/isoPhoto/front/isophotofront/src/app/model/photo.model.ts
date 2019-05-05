import { Tag } from './tag.model';
import { Photographe } from './photographe.model';
import { Don } from './don.model';
import { Commentaire } from './commentaire.model';

export class Photo{

    private id : number;
    private description : string;
    private image : string;
    private titre : string;
    private donListe : Don[];
    private commentaireListe : Commentaire[];
    private tagListe : Tag[];
    private photographe : Photographe;

    constructor(id : number,
                description : string,
                image : string,
                titre : string,
                donListe : Don[],
                commentaireListe : Commentaire[],
                tagListe : Tag[],
                photographe : Photographe)
                {
                    this.id = id;
                    this.description = description;
                    this.image = image;
                    this.titre = titre;
                    this.donListe = donListe;
                    this.commentaireListe = commentaireListe;
                    this.tagListe = tagListe;
                    this.photographe = photographe;
                }

    public getId(): number {
        return this.id;
    }

    public setId(id: number): void {
        this.id = id;
    }

    public getDescription(): string {
        return this.description;
    }

    public setDescription(description: string): void {
        this.description = description;
    }

    public getImage(): string {
        return this.image;
    }

    public setImage(image: string): void {
        this.image = image;
    }

    public getTitre(): string {
        return this.titre;
    }

    public setTitre(titre: string): void {
        this.titre = titre;
    }

    public getDonListe(): Don[] {
        return this.donListe;
    }

    public setDonListe(donListe: Don[]): void {
        this.donListe = donListe;
    }

    public getCommentaireListe(): Commentaire[] {
        return this.commentaireListe;
    }

    public setCommentaireListe(commentaireListe: Commentaire[]): void {
        this.commentaireListe = commentaireListe;
    }

    public getTagListe(): Tag[] {
        return this.tagListe;
    }

    public setTagListe(tagListe: Tag[]): void {
        this.tagListe = tagListe;
    }

    public getPhotographe(): Photographe {
        return this.photographe;
    }

    public setPhotographe(photographe: Photographe): void {
        this.photographe = photographe;
    }


}


