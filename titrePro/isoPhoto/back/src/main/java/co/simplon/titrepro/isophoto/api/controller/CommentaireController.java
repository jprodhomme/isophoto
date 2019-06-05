package co.simplon.titrepro.isophoto.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.titrepro.isophoto.model.Commentaire;
import co.simplon.titrepro.isophoto.repository.CommentaireRepository;
import co.simplon.titrepro.isophoto.repository.PhotoRepository;
import co.simplon.titrepro.isophoto.repository.PhotographeRepository;
import co.simplon.titrepro.isophoto.services.CommentaireService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class CommentaireController {

	@Autowired
	CommentaireRepository commentaireRepo;
	
	@Autowired
	PhotoRepository photoRepo;
	
	@Autowired
	PhotographeRepository photographeRepo;

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

	@GetMapping("/getcommentairesbyphotoid/{photoId}")
	public ResponseEntity<?> getCommentairesByPhotoId(@PathVariable Long photoId) {
		List<Commentaire> listeCommentaire = photoRepo.findById(photoId).get().getCommentaires();
		try {
			listeCommentaire = (List<Commentaire>) commentaireRepo.findAll();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(listeCommentaire);
	}
	

	@PutMapping("/addcommentaire/{idPhoto}/{photoPseudo}/{commentaireBox}")
	public ResponseEntity<?> addCommentaire(@PathVariable Long idPhoto,
											@PathVariable String photoPseudo,
											@PathVariable String commentaireBox) {

		try {
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(this.commentaireService.addCommentaire(idPhoto, photoPseudo, commentaireBox));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/deletecommentaire/{comId}/{pseudo}/{photoId}")
	public ResponseEntity<?> delCommentaire(@PathVariable Long comId,
											@PathVariable String pseudo,
											@PathVariable Long photoId) {
		
	try{
			if(pseudo.equals("Admin")) {

				this.commentaireRepo.deleteById(comId);
			}
				
	    			
		if (this.commentaireRepo.findById(comId).get().getPhotographe().getPseudo().equals( pseudo) ||
				(this.photoRepo.findById(photoId).get().getPhotographe().getPseudo().equals( pseudo)) 					)
		
			this.commentaireRepo.deleteById(comId);
		
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(null);

		}


}
