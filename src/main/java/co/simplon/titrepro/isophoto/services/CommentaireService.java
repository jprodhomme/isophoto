package co.simplon.titrepro.isophoto.services;

import co.simplon.titrepro.isophoto.model.Photo;

public interface CommentaireService {
//	Commentaire findByCommenaire(String commentaire);
	
	Photo addCommentaire( long idPhoto, String commentaireString);
	
}
