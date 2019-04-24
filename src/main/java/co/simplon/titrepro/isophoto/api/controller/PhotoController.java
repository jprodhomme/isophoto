package co.simplon.titrepro.isophoto.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.titrepro.isophoto.model.Photo;
import co.simplon.titrepro.isophoto.model.Photographe;
import co.simplon.titrepro.isophoto.model.Tag;
import co.simplon.titrepro.isophoto.repository.PhotoRepository;
import co.simplon.titrepro.isophoto.repository.PhotographeRepository;
import co.simplon.titrepro.isophoto.repository.TagRepository;
import co.simplon.titrepro.isophoto.services.PhotoService;
import co.simplon.titrepro.isophoto.services.PhotographeService;

@RestController
@RequestMapping("/api")
public class PhotoController {
	@Autowired
	PhotoRepository photoRepo;

	@Autowired
	TagRepository tagRepo;

	@Autowired
	PhotographeRepository photographeRepo;

	private PhotographeService photographeService;
	private PhotoService photoService;

	public PhotoController(PhotographeService photographeService, PhotoService photoService) {
		this.photographeService = photographeService;
		this.photoService = photoService;
	}

	/**
	 * Méthode GET pour retourner toutes les PHOTOS dispo en BD
	 * 
	 * @return
	 */
	@GetMapping("/photos")
	@CrossOrigin(origins = "http://localhost:4200")
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
	@GetMapping("/photosbytag/{tag}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getPhotosbyId(@PathVariable String tag) {

		List<Photo> listePhoto = null;
		try {
			Optional<Tag> tagNom = tagRepo.findByTag(tag);
			if (tagNom.isPresent()) {
				listePhoto = (List<Photo>) tagNom.get().getPhotos();

			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		System.out.println("----------------------------------------");
		System.out.println("Tag : " + tag);
		System.out.println("Liste Photo avec le Tag " + tag + listePhoto);
		System.out.println("----------------------------------------");
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
		System.out.println("----------------------------------------");
		System.out.println("Photographe : " + nom);
		System.out.println("Liste Photo de " + nom + listePhoto);
		System.out.println("----------------------------------------");

		return ResponseEntity.status(HttpStatus.OK).body(listePhoto);
	}

	/**
	 * 
	 * @param description
	 * @param image
	 * @param categorie
	 * @param titre
	 * @return
	 */
	@PostMapping("/addphoto")
	public ResponseEntity<?> addPhoto(@Valid String description,
			 						  @Valid String titre, 
			 						  @Valid String image, 
			 						  @Valid ArrayList<String> tagsString,
									  @Valid String pseudo) {									

		try {

			return ResponseEntity.status(HttpStatus.OK)
					.body(this.photoService.savePhoto(description, titre, image, tagsString, pseudo));

		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}

}
