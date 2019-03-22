package co.simplon.titrepro.isophoto.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.titrepro.isophoto.model.Categorie;
import co.simplon.titrepro.isophoto.model.Exif;
import co.simplon.titrepro.isophoto.model.Photo;
import co.simplon.titrepro.isophoto.model.Photographe;
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

	/**
	 * Méthode GET pour retourner toutes les PHOTOS dispo en BD
	 * 
	 * @return
	 */
	@GetMapping("/photos")
	public ResponseEntity<?> getAllPhoto() {
		List<Photo> listePhoto = photoRepo.findAll();

		try {
			listePhoto = (List<Photo>) photoRepo.findAll();

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.OK).body(listePhoto);
	}

	/**
	 * Méthode GET pour retourner toutes les PHOTOS d'une CATEGORIE en renseignant
	 * le NOM de la catégorie
	 * 
	 * @param nomCat
	 * @param id
	 * @return
	 */
	@GetMapping("/photosbycat/{nom}")
	public ResponseEntity<?> getPhotosbyId(@PathVariable String nom) {

		List<Photo> listePhoto = null;
		try {
			Optional<Categorie> catNom = catRepo.findByNom(nom);
			if (catNom.isPresent()) {
				listePhoto = (List<Photo>) catNom.get().getPhotos();

			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(listePhoto);
	}

	/**
	 * Méthode GET pour récupérer toutes les PHOTOS d'un PHOTOGRAPHE
	 * 
	 * @param nom
	 * @return
	 */
	@GetMapping("/photosbyphotographe/{nom}")
	public ResponseEntity<?> getPhotosbyPhotographe(@PathVariable String nom) {

		List<Photo> listePhoto = null;
		try {
			Optional<Photographe> photographeNom = photographeRepo.findByNom(nom);
			if (photographeNom.isPresent()) {
				listePhoto = (List<Photo>) photographeNom.get().getPhotos();

			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(listePhoto);
	}

	/**
	 * Méthode POST pour ajouter un PHOTO
	 * 
	 * @param id
	 * @param aVendre
	 * @param description
	 * @param image
	 * @param prix
	 * @param titre
	 * @return
	 */
	@PostMapping("/addphoto")
	public ResponseEntity<?> addPhoto(@Valid boolean aVendre, 
									  @Valid String description,
									  @Valid String image,
									  @Valid float prix,
									  @Valid String titre) {

		Photo photo = new Photo(aVendre, description, image, prix, titre);
		photoRepo.save(photo);

		try {
			// authoritiesRepo.save(authorities);
			return ResponseEntity.status(HttpStatus.OK).body(null);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}

}
