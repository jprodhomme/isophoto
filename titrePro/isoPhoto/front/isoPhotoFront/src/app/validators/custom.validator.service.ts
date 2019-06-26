import { AbstractControl } from '@angular/forms';

export class CustomValidator {
    static pseudoValidator(pseudo: string[]) {
      console.log("lolo");
        return (control: AbstractControl): { [key: string]: any } | null => {
            let isValid = false;
            if (control.value) {
                const checkPseudo: string = control.value;
                isValid = !(pseudo.find(pseudo => pseudo.toLowerCase() === checkPseudo.toLowerCase()));
            }
            console.log(isValid);
            if (isValid) {
              
                return null;
            } else {
                return { pseudo: true };
            }
        };
    }
  }
