import { Photo } from './photo.model';

export class Photographe{

    private id : number;
	private nom : string;
	private prenom : string;
	private pseudo : string;
	private email : string;
    private password : string; 
    private photoListe : Photo[];

    constructor(id : number,
        nom : string,
        prenom : string,
        pseudo : string,
        email : string,
        password : string,
        photoListe : Photo[]){

            this.id = id;
            this.nom = nom;
            this.prenom = prenom;
            this.pseudo = pseudo;
            this.email = email;
            this.password = password;
            this.photoListe = photoListe;

        }

    public getId(): number {
        return this.id;
    }

    public setId(id: number): void {
        this.id = id;
    }

    public getNom(): string {
        return this.nom;
    }

    public setNom(nom: string): void {
        this.nom = nom;
    }

    public getPrenom(): string {
        return this.prenom;
    }

    public setPrenom(prenom: string): void {
        this.prenom = prenom;
    }

    public getPseudo(): string {
        return this.pseudo;
    }

    public setPseudo(pseudo: string): void {
        this.pseudo = pseudo;
    }

    public getEmail(): string {
        return this.email;
    }

    public setEmail(email: string): void {
        this.email = email;
    }

    public getPassword(): string {
        return this.password;
    }

    public setPassword(password: string): void {
        this.password = password;
    }

    public getPhotoListe(): Photo[] {
        return this.photoListe;
    }

    public setPhotoListe(photoListe: Photo[]): void {
        this.photoListe = photoListe;
    }   
}