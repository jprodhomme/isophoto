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
	
//	private CommentRepository commentRepo;

	public CommentaireServiceImpl(CommentaireRepository commentaireRepo, 
								  PhotoRepository photoRepo) {
		this.commentaireRepo = commentaireRepo;
		this.photoRepo = photoRepo;
		
	}
	
	@Override
	public Photo addCommentaire(long idPhoto, String commentaireString) {
		
		Photo photo = photoRepo.findById(idPhoto).get();
		
//		Comment com = new Comment(commentaireString);
		Commentaire com = new Commentaire(commentaireString);
//				
//		List<Comment> comments = photo.getCommentaires();
		List<Commentaire> commentaires = photo.getCommentaires();
		
//		comments.add(com);
		commentaires.add(com);
		
		
//		Comment comment = this.commentRepo.save(com);
		Commentaire comment = this.commentaireRepo.save(com);
		
		
		comment.setPhoto(photo);
		
//		this.commentRepo.save(comment);
		this.commentaireRepo.save(comment);
		
		this.photoRepo.save(photo);
		

		
		return photo;


	}


}




