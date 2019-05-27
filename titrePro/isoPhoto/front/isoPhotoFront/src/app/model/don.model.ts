import { Photo } from './photo.model';

export class Don {

    constructor(public id?: number,
        public commentaire?: string,
        public dateDon?: Date,
        public montant?: number,
        public photo?: Photo) {
    }
}