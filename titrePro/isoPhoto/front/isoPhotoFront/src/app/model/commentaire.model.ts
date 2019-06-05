import { Photo } from './photo.model';
import { Photographe } from './photographe.model';

export class Commentaire{

    constructor(public id? : number,
                public commentaire? : string,
                public photo? : Photo,
                public photographe? : Photographe){
        }
}