package co.simplon.titrepro.isophoto.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.titrepro.isophoto.model.Commentaire;
import co.simplon.titrepro.isophoto.repository.CommentaireRepository;
import co.simplon.titrepro.isophoto.services.PhotoService;

@RestController
@RequestMapping("/api")
public class CommentaireController {
	
	@Autowired
	CommentaireRepository commentaireRepo;
	
	private PhotoService photoService;
	
	public CommentaireController(PhotoService photoService) {
		this.photoService = photoService;
	}
	
	
	@GetMapping("/getcommentaires")
	public ResponseEntity<?> getAllCommentaires() {
		List<Commentaire> listeCommentaire = commentaireRepo.findAll();
		try {
			listeCommentaire = (List<Commentaire>) commentaireRepo.findAll();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(listeCommentaire);
	}

	
}
