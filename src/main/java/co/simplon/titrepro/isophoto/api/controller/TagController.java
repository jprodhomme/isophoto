package co.simplon.titrepro.isophoto.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import co.simplon.titrepro.isophoto.model.Tag;
import co.simplon.titrepro.isophoto.repository.AuthorityRepository;
import co.simplon.titrepro.isophoto.repository.PhotoRepository;
import co.simplon.titrepro.isophoto.repository.PhotographeRepository;
import co.simplon.titrepro.isophoto.repository.TagRepository;

@RestController
@RequestMapping("/api")
public class TagController {

	@Autowired
	PhotoRepository photoRepo;

	@Autowired
	TagRepository tagRepo;

	@Autowired
	PhotographeRepository photographeRepo;

	@Autowired
	AuthorityRepository authorityRepo;

	/**
	 * Méthode POST pour ajouter une CATEGORIE
	 * 
	 * @param id
	 * @param nom
	 * @return
	 */
	@PostMapping("/addtag")
	public ResponseEntity<?> addTag(@Valid String nom) {

		Tag tag = new Tag(nom);
		tagRepo.save(tag);

		try {
			// authoritiesRepo.save(authorities);
			return ResponseEntity.status(HttpStatus.OK).body(null);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}

	}

