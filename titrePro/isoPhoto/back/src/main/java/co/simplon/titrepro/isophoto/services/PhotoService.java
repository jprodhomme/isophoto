package co.simplon.titrepro.isophoto.services;

import java.util.List;

import co.simplon.titrepro.isophoto.model.Commentaire;
import co.simplon.titrepro.isophoto.model.Photo;

public interface PhotoService {
	
	public Photo savePhoto( String description, 
						    String titre,  
						    String image, 
						    String tagsString, 
						    String pseudo);



	Photo addPhoto(Photo photo, String pseudo);
	
	public List<Commentaire> photoCommentaire( Long idPhoto);
	
//	public List<Integer> photoIdCOmmentaire(Long idPhoto);

	public String photographebyphotoid(Long idPhoto);
}
