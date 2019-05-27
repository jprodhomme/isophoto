import { Photo } from './photo.model';
import { Authority } from './authority.model';

export class Photographe {

    constructor(public id?: number,
                public nom?: string,
                public prenom?: string,
                public pseudo?: string,
                public email?: string,
                public password?: string,
                public photoListe?: Photo[],
                public authority?: Authority) {
    }
}