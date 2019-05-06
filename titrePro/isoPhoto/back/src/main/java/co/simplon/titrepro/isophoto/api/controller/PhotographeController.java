package co.simplon.titrepro.isophoto.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.titrepro.isophoto.model.Photographe;
import co.simplon.titrepro.isophoto.repository.PhotographeRepository;
import co.simplon.titrepro.isophoto.services.PhotoService;
import co.simplon.titrepro.isophoto.services.PhotographeService;

@RestController
@RequestMapping("/api")
public class PhotographeController {
	
	@Autowired
	PhotographeRepository photographeRepo;
	
	private PhotographeService photographeService;
	
	public PhotographeController(PhotographeService photographeService, PhotoService photoService) {
		this.photographeService = photographeService;
	}

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
	 * 
	 * @param id
	 * @param email
	 * @param nom
	 * @param password
	 * @param prenom
	 * @param pseudo
	 * @return
	 */
	@PostMapping("/addphotographe")
	public ResponseEntity<?> addPhotographes(@Valid String nom,
											 @Valid String prenom,
											 @Valid String pseudo,
											 @Valid String password,											  
											 @Valid String email) {
		try {

			return ResponseEntity.status(HttpStatus.OK)
					.body(this.photographeService.savePhotographe(nom, prenom, pseudo, email, password));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}

	@DeleteMapping("/deletephotographe")
	public ResponseEntity<?> delUser(@Valid long delId) {

		this.photographeRepo.deleteById(delId);

		List<Photographe> photographeList = null;
		try {
			photographeList = (List<Photographe>) photographeRepo.findAll();

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(photographeList);

	}

}
