import { Tag } from './tag.model';
import { Photographe } from './photographe.model';
import { Don } from './don.model';
import { Commentaire } from './commentaire.model';

export class Photo{

    constructor(
                public id? : number,
                public description? : string,
                public image? : string,
                public titre? : string,
                public donListe? : Don[],
                public commentaires? : Commentaire[],
                public tagListe? : Tag[],
                public photographe? : Photographe) { }

}