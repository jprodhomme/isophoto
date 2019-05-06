package co.simplon.titrepro.isophoto.services;

import java.util.List;

import org.springframework.stereotype.Service;

//import co.simplon.titrepro.isophoto.model.Comment;
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

		Commentaire com = new Commentaire(commentaireString);

		List<Commentaire> commentaires = photo.getCommentaires();

		commentaires.add(com);

		Commentaire comment = this.commentaireRepo.save(com);

		comment.setPhoto(photo);

		this.commentaireRepo.save(comment);

		this.photoRepo.save(photo);

		return photo;

	}

}
