
import { Authority } from './authority.model';

export class User {

        constructor(public idUser: number,
                    public username: String,
                    public mdp: String,
                    public Authority: Authority) {

        }
}