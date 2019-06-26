import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ErrorsService {

  constructor() { }
}

export const errorMessages: { [key : string] : string} = {
  pseudoSame : 'Ce pseudo est déjà pris, veuillez en choisir un autre',
}