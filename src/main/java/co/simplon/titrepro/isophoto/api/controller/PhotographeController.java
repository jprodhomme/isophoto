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

import co.simplon.titrepro.isophoto.model.Photographe;
import co.simplon.titrepro.isophoto.repository.AuthorityRepository;
import co.simplon.titrepro.isophoto.repository.PhotoRepository;
import co.simplon.titrepro.isophoto.repository.PhotographeRepository;
import co.simplon.titrepro.isophoto.repository.TagRepository;

@RestController
@RequestMapping("/api")
public class PhotographeController {
	
	@Autowired
	PhotoRepository photoRepo;

	@Autowired
	TagRepository tagsRepo;
	
	@Autowired
	PhotographeRepository photographeRepo;
	
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

		return ResponseEntity.status(HttpStatus.OK).body(listePhotographes);
	}
	
	
	/**
	 * Méthode POST pour ajouter un PHOTOGRAPHE
	 * @param id
	 * @param email
	 * @param nom
	 * @param password
	 * @param prenom
	 * @param pseudo
	 * @return
	 */
	@PostMapping("/addphotographe")
	public ResponseEntity<?> addPhotographes(  
											  @Valid String nom,
											  @Valid String prenom,
											  @Valid String pseudo,
											  @Valid String password,											  
											  @Valid String email) {
		
		Photographe photographe = new Photographe(nom, prenom, pseudo, password, email);
		photographeRepo.save(photographe);
		
	
		try {
			//authoritiesRepo.save(authorities);
			return ResponseEntity.status(HttpStatus.OK).body(null);
			
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);	
		}	
				
	}
	
		

}
