package co.simplon.titrepro.isophoto.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.titrepro.isophoto.model.Tag;
import co.simplon.titrepro.isophoto.repository.TagRepository;
import co.simplon.titrepro.isophoto.services.TagService;

@RestController
@RequestMapping("/api")
public class TagController {

	@Autowired
	TagRepository tagRepo;
	
	private TagService tagService;
	
	public TagController(TagService tagService) {
		this.tagService = tagService; 
	}
	
	@GetMapping("/gettags")
	public ResponseEntity<?> getAllTag() {
		List<Tag> listeTag = tagRepo.findAll();
		try {
			listeTag = (List<Tag>) tagRepo.findAll();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(listeTag);
	}

	/**
	 * Méthode POST pour ajouter une CATEGORIE
	 * 
	 * @param id
	 * @param nom
	 * @return
	 */
	@PostMapping("/addtag")
	public ResponseEntity<?> addTag(@Valid String tagString) {

			try {
			
			return ResponseEntity.status(HttpStatus.OK).body(this.tagService.saveTag(tagString));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}
	
	

	}

