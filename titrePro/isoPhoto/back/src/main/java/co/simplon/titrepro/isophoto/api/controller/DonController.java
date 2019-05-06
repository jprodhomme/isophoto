package co.simplon.titrepro.isophoto.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.titrepro.isophoto.model.Don;
import co.simplon.titrepro.isophoto.repository.DonRepository;
import co.simplon.titrepro.isophoto.services.DonService;

@RestController
@RequestMapping("/api")
public class DonController {

	@Autowired
	DonRepository donRepo;

	private DonService donService;

	public DonController(DonService donService) {
		this.donService = donService;
	}

	@GetMapping("/getdons")
	public ResponseEntity<?> getAllDons() {
		List<Don> listeDons = donRepo.findAll();
		try {
			listeDons = (List<Don>) donRepo.findAll();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(listeDons);

	}

	@PutMapping("/adddon")
	public ResponseEntity<?> addDon(@Valid Long idPhoto,
									@Valid String commentaire,
									@Valid Integer montant) {

		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.donService.addDon(idPhoto, commentaire, montant));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
}
