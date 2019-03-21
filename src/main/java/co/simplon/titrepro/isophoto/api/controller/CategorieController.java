package co.simplon.titrepro.isophoto.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.titrepro.isophoto.model.Categorie;
import co.simplon.titrepro.isophoto.repository.AuthorityRepository;
import co.simplon.titrepro.isophoto.repository.CategorieRepository;
import co.simplon.titrepro.isophoto.repository.ExifRepository;
import co.simplon.titrepro.isophoto.repository.PhotoRepository;
import co.simplon.titrepro.isophoto.repository.PhotographeRepository;

@RestController
@RequestMapping("/api")
public class CategorieController {

	@Autowired
	PhotoRepository photoRepo;

	@Autowired
	CategorieRepository catRepo;

	@Autowired
	PhotographeRepository photographeRepo;

	@Autowired
	ExifRepository exifRepo;

	@Autowired
	AuthorityRepository authorityRepo;
	
	
	/**
	 * Méthode POST pour ajouter une CATEGORIE
	 * @param id
	 * @param nom
	 * @return
	 */
	@PostMapping("/addcategorie")
	public ResponseEntity<?> addCategorie(@Valid Integer id, @Valid String nom) {

		Categorie categorie = new Categorie(id, nom);
		catRepo.save(categorie);

		try {
			// authoritiesRepo.save(authorities);
			return ResponseEntity.status(HttpStatus.OK).body(null);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}

}
