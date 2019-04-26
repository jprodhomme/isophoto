	package co.simplon.titrepro.isophoto.api.controller;

import java.util.List;

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
import co.simplon.titrepro.isophoto.services.PhotoService;
import co.simplon.titrepro.isophoto.services.PhotographeService;
import co.simplon.titrepro.isophoto.services.TagService;

@RestController
@RequestMapping("/api")
public class PhotoController {
	@Autowired
	PhotoRepository photoRepo;

	private PhotographeService photographeService;
	private PhotoService photoService;
	private TagService tagService;

	public PhotoController(PhotographeService photographeService, 
						   PhotoService photoService, 
						   TagService tagService) {
		this.photographeService = photographeService;
		this.photoService = photoService;
		this.tagService = tagService;
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
	public ResponseEntity<?> getPhotosbyTag(@PathVariable String tag) {

		List<Photo> listePhoto = null;

		try {
			Tag tagString = tagService.findByTag(tag);

			listePhoto = (List<Photo>) tagString.getPhotos();

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		System.out.println(listePhoto.toString());
		return ResponseEntity.status(HttpStatus.OK).body(listePhoto);

	}

	/**
	 * Méthode GET pour récupérer toutes les PHOTOS d'un PHOTOGRAPHE
	 * 
	 * @param nom
	 * @return
	 */
	@GetMapping("/photosbyphotographe/{pseudo}")
	public ResponseEntity<?> getPhotosbyPhotographe(@PathVariable String pseudo) {

		List<Photo> listePhoto = null;

		try {
			Photographe photographeNom = photographeService.findByPseudo(pseudo);

			listePhoto = (List<Photo>) photographeNom.getPhotos();

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.OK).body(listePhoto);
	}

	/**
	 * Méthode POST pour ajouter une photo en lui donnant les paramètres suivants.
	 *
	 * @param description
	 * @param titre
	 * @param image
	 * @param tagsString
	 * @param pseudo
	 * @return
	 */
	@PostMapping("/addphoto")
	public ResponseEntity<?> addPhoto(@Valid String description,
			 						  @Valid String titre, 
			 						  @Valid String image, 
			 						  @Valid String tagsString,
									  @Valid String pseudo) {									
		try {
			System.out.println("/addPhoto : " + tagsString);
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(this.photoService.savePhoto(description, titre, image, tagsString, pseudo));

		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}

}
