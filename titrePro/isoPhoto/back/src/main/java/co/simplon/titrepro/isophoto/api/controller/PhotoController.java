package co.simplon.titrepro.isophoto.api.controller;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.titrepro.isophoto.model.Photo;
import co.simplon.titrepro.isophoto.model.Photographe;
import co.simplon.titrepro.isophoto.model.Tag;
import co.simplon.titrepro.isophoto.repository.PhotoRepository;
import co.simplon.titrepro.isophoto.repository.PhotographeRepository;
import co.simplon.titrepro.isophoto.repository.TagRepository;
import co.simplon.titrepro.isophoto.services.FileService;
import co.simplon.titrepro.isophoto.services.PhotoService;
import co.simplon.titrepro.isophoto.services.PhotographeService;
import co.simplon.titrepro.isophoto.services.TagService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class PhotoController {
	@Autowired
	PhotoRepository photoRepo;
	
	@Autowired
	PhotographeRepository photographeRepo;
	
	@Autowired
	TagRepository tagRepo;

	private PhotographeService photographeService;
	private PhotoService photoService;
	private TagService tagService;
	private FileService fileService;
	

	public PhotoController(PhotographeService photographeService, 
						   PhotoService photoService, 
						   TagService tagService, 
						   FileService fileService) {

		this.photographeService = photographeService;
		this.photoService = photoService;
		this.tagService = tagService;	
		this.fileService = fileService;
	}

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

	@GetMapping("/photosbyid/{idPhoto}")
	public ResponseEntity<?> getPhotoById(@PathVariable Long idPhoto) {

		try {
			return ResponseEntity.status(HttpStatus.OK).body(photoRepo.findById(idPhoto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@GetMapping("/photosbytag")
	public ResponseEntity<?> getPhotosbyTag(@Valid String tag) {

		List<Photo> listePhoto = null;

		try {
			Tag tagString = tagService.findByTag(tag);

			listePhoto = (List<Photo>) tagString.getPhotos();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(listePhoto);
	}

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
	 * 
	 * @param file
	 * @param description
	 * @param titre
	 * @param image
	 * @param tagsString
	 * @param pseudo
	 * @return
	 */
	 
	@PostMapping("/addphoto")
	public ResponseEntity<?> addPhoto(@RequestBody Photo photo, String pseudo) {		
		
		
	
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(this.photoService.addPhoto(photo, pseudo));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	@PreAuthorize("hasAuthority('photographe')")
		@PostMapping("/create/{tag}/{pseudo}")
	public ResponseEntity<?> addNewPhoto(@RequestBody Photo photo,
										 @PathVariable String tag,
										 @PathVariable String pseudo) {
		
		List<Tag> tagList = new ArrayList<Tag>();
		for (String tagString : tag.split(",")) {

			Tag foundTag = this.tagRepo.findByTag(tagString);
			
			if (foundTag == null) {
				
				foundTag = this.tagRepo.save(new Tag(tagString.trim()));
			}
			tagList.add(foundTag);
			

		}
	
		Photo newPhoto = null;
		
		String titre = photo.getTitre();
		String description = photo.getDescription();
		String  image = photo.getImage();
		System.out.println(photo.getImage(	));

		newPhoto = new Photo(titre, description, image, tagList, this.photographeRepo.findByPseudo(pseudo));
		this.photoRepo.save(newPhoto);

		return ResponseEntity.status(HttpStatus.OK).body(newPhoto);
	}


	/**
	 * 
	 * @param file
	 * @return
	 */
	@PostMapping("/photo/uploadphoto")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			fileService.store(file);
			message = "You successfully uploaded " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "Fail to upload Profile Picture" + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
		
		
	}

	@DeleteMapping("/deletephoto")
	public ResponseEntity<?> delPhoto(@Valid long delId) {
		this.photoRepo.deleteById(delId);
		List<Photo> photoList = null;
		try {
			photoList = (List<Photo>) photoRepo.findAll();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(photoList);
	}
}
