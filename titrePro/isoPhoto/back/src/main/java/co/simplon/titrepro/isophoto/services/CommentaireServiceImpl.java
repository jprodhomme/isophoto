package co.simplon.titrepro.isophoto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import co.simplon.titrepro.isophoto.model.Comment;
import co.simplon.titrepro.isophoto.model.Commentaire;
import co.simplon.titrepro.isophoto.model.Photo;
import co.simplon.titrepro.isophoto.model.Photographe;
import co.simplon.titrepro.isophoto.repository.CommentaireRepository;
import co.simplon.titrepro.isophoto.repository.PhotoRepository;
import co.simplon.titrepro.isophoto.repository.PhotographeRepository;

@Service
public class CommentaireServiceImpl implements CommentaireService {
	@Autowired
	private CommentaireRepository commentaireRepo;
	@Autowired
	private PhotoRepository photoRepo;
	@Autowired
	private PhotographeRepository photographeRepo;

	public CommentaireServiceImpl(CommentaireRepository commentaireRepo,
								  PhotoRepository photoRepo,
								  PhotographeRepository photographeRepo) {
		
		this.commentaireRepo = commentaireRepo;
		this.photoRepo = photoRepo;

	}

	
	@Override
	public Photo addCommentaire(long idPhoto, String photoPseudo, String commentaireString) {

		Photo photo = photoRepo.findById(idPhoto).get();
		
		List<Commentaire> commentaires = photo.getCommentaires();
		
		Commentaire com = new Commentaire(commentaireString);
		
		commentaires.add(com);
		
		this.commentaireRepo.save(com);
		
		com.setPhoto(photo);		

		Photographe photographe = this.photographeRepo.findByPseudo(photoPseudo);
		
		com.setPhotographe(photographe);
		
		this.photographeRepo.save(photographe);
		
		this.commentaireRepo.save(com);
		
		this.photoRepo.save(photo);

		return photo;

	}

}
