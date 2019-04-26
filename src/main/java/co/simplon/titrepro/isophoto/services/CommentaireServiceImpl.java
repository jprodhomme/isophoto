package co.simplon.titrepro.isophoto.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.simplon.titrepro.isophoto.model.Commentaire;
import co.simplon.titrepro.isophoto.model.Photo;
import co.simplon.titrepro.isophoto.repository.CommentaireRepository;
import co.simplon.titrepro.isophoto.repository.PhotoRepository;

@Service
public class CommentaireServiceImpl implements CommentaireService {

	private CommentaireRepository commentaireRepo;
	private PhotoRepository photoRepo;

	public CommentaireServiceImpl(CommentaireRepository commentaireRepo, 
								  PhotoRepository photoRepo) {
		this.commentaireRepo = commentaireRepo;
		this.photoRepo = photoRepo;
	}
	
	@Override
	public Photo addCommentaire(long idPhoto, String commentaireString) {
		
		Photo photo = photoRepo.findById(idPhoto).get();
		
		List<Commentaire>comPhoto = photo.getCommentaires();	
			
		Commentaire com = new Commentaire(commentaireString);
		
		comPhoto.add(com);
		this.commentaireRepo.save(com);
		
		this.photoRepo.save(photo);
		
		return photo;


	}


}




