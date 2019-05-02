package co.simplon.titrepro.isophoto.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.titrepro.isophoto.model.Commentaire;
import co.simplon.titrepro.isophoto.repository.CommentaireRepository;
import co.simplon.titrepro.isophoto.services.CommentaireService;

@RestController
@RequestMapping("/api")
public class CommentaireController {
	
	@Autowired
	CommentaireRepository commentaireRepo;
	
	private CommentaireService commentaireService;

	
	public CommentaireController(CommentaireService commentaireService) {
		this.commentaireService = commentaireService;
		
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

	
	
	
	@PutMapping("/addcommentaire")
	public ResponseEntity<?> addCommentaire(@Valid Long idPhoto, @Valid String commentaireString) {

		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(this.commentaireService.addCommentaire(idPhoto, commentaireString));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	} 
	
}
