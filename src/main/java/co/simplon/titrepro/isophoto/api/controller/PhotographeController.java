package co.simplon.titrepro.isophoto.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.simplon.titrepro.isophoto.model.Photo;
import co.simplon.titrepro.isophoto.model.Photographe;
import co.simplon.titrepro.isophoto.repository.AuthorityRepository;
import co.simplon.titrepro.isophoto.repository.CategorieRepository;
import co.simplon.titrepro.isophoto.repository.ExifRepository;
import co.simplon.titrepro.isophoto.repository.PhotoRepository;
import co.simplon.titrepro.isophoto.repository.PhotographeRepository;

@RestController
@RequestMapping("/api")
public class PhotographeController {
	
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
	 * Méthode GET pour retourner tous les photographes présents en BD
	 * @return
	 */
	@GetMapping("/photographes")
	public ResponseEntity<?> getAllPhotographes() {
		List<Photographe> listePhotographes = photographeRepo.findAll();

		try {
			listePhotographes = (List<Photographe>) photographeRepo.findAll();

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		System.out.println("Liste Photo => " + listePhotographes);
		return ResponseEntity.status(HttpStatus.OK).body(listePhotographes);
	}


}
