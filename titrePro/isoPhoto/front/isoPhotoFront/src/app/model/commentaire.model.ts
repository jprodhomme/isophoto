import { Photo } from './photo.model';

export class Commentaire{

    constructor(public id? : number,
                public commentaire? : string,
                public photo? : Photo){
        }
}