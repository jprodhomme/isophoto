package co.simplon.titrepro.isophoto.services;

import co.simplon.titrepro.isophoto.model.Photo;

public interface DonService {

	Photo addDon(Long idPhoto, 
				 String commentaire, 
				 Integer montant);


}
