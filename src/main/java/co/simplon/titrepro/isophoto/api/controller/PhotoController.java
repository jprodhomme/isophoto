package co.simplon.titrepro.isophoto.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.simplon.titrepro.isophoto.model.Categorie;
import co.simplon.titrepro.isophoto.model.Photo;
import co.simplon.titrepro.isophoto.repository.AuthorityRepository;
import co.simplon.titrepro.isophoto.repository.CategorieRepository;
import co.simplon.titrepro.isophoto.repository.ExifRepository;
import co.simplon.titrepro.isophoto.repository.PhotoRepository;
import co.simplon.titrepro.isophoto.repository.PhotographeRepository;

@RestController
@RequestMapping("/api")
public class PhotoController {

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
	
	@JsonIgnore
	@GetMapping("/photos")
	public ResponseEntity<?> getAllPhoto() {
		List<Photo> listePhoto = photoRepo.findAll();

		try {
			listePhoto = (List<Photo>) photoRepo.findAll();

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		System.out.println("Liste Photo => " + listePhoto);
		return ResponseEntity.status(HttpStatus.OK).body(listePhoto);
	}

	@GetMapping("/photos/{id}")
	public ResponseEntity<?> getPhotosbyId(@PathVariable Integer id) {

		Categorie cat = null;

		try {
			cat = catRepo.findById(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(cat);

	}

}
