package co.simplon.titrepro.isophoto.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.titrepro.isophoto.dto.JsonWebToken;
import co.simplon.titrepro.isophoto.dto.PhotographeDto;
import co.simplon.titrepro.isophoto.exception.ExistingUsernameException;
import co.simplon.titrepro.isophoto.exception.InvalidCredentialsException;
import co.simplon.titrepro.isophoto.model.Photographe;
import co.simplon.titrepro.isophoto.repository.PhotographeRepository;
import co.simplon.titrepro.isophoto.services.PhotographeService;

@RestController
@RequestMapping("/api")
public class PhotographeController {
	
	@Autowired
	PhotographeRepository photographeRepo;
	
	@Autowired
	private PhotographeService photographeService;
	
	private BCryptPasswordEncoder passwordEncoder;
	
	public PhotographeController(PhotographeService photographeService, BCryptPasswordEncoder passwordEncoder) {
		this.photographeService = photographeService;
		this.passwordEncoder = passwordEncoder;
	}
	
	
	
    /**
     * Method pour enregistrer un nouveau photographe en base
     * @param photographe le nouveau photographe à enregistrer
     * @return un JWT si le sign up est OK ou un code réponse KO
     */
    @PostMapping("/sign-up")
    public ResponseEntity<JsonWebToken> signUp(@RequestBody Photographe photographe) {
        try {
        	System.out.println(photographe.getPassword());
            return ResponseEntity.ok(new JsonWebToken(photographeService.signup(photographe)));
        } catch (ExistingUsernameException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Methode pour connecter un photographe (déjà existant)
     * @param le photographe à logger 
     * @return un JWT si le sign-in est OK ou un code réponse KO 
     */
    @PostMapping("/sign-in")
    public ResponseEntity<JsonWebToken> signIn(@RequestBody Photographe photographe) {
        try {
            return ResponseEntity.ok(new JsonWebToken(photographeService.signin(photographe.getPseudo(), photographe.getPassword())));
        } catch (InvalidCredentialsException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Methode pour retrouner tous les photographes présents en base
     * Méthode réservée aux Admins
     * @return la liste de tous les photographes présents en base.
     */
    @GetMapping("/photographe")
    @PreAuthorize("hasAuthority('admin')")
    public List<PhotographeDto> getAllUsers() {
        return photographeService.findAllPhotographe().stream().map(photographe -> new PhotographeDto(photographe.getPseudo(), photographe.getAuthority())).collect(Collectors.toList());
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
	 * Méthode POST pour ajouter un PHOTOGRAPHE un nouveau photographe
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
