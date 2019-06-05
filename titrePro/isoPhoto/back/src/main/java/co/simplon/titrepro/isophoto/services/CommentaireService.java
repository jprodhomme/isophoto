package co.simplon.titrepro.isophoto.services;

import co.simplon.titrepro.isophoto.model.Photo;

public interface CommentaireService {

	Photo addCommentaire(long idPhoto,String pseudo, String commentaireString);
}
