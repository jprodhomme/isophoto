package co.simplon.titrepro.isophoto.services;

import org.springframework.stereotype.Service;

import co.simplon.titrepro.isophoto.model.Photographe;
import co.simplon.titrepro.isophoto.repository.PhotographeRepository;

@Service
public class PhotographeServiceImpl implements PhotographeService {

	private PhotographeRepository photographeRepo;

	public PhotographeServiceImpl(PhotographeRepository photographeRepo) {
		this.photographeRepo = photographeRepo;

	}

	@Override
	public Photographe findByPseudo(String pseudo) {
		return this.photographeRepo.findByPseudo(pseudo);

	}

	@Override
	public Photographe savePhotographe(String nom, 
			   						   String prenom, 
			   						   String pseudo, 
			   						   String email, 
			   						   String password) {

		Photographe photographe = new Photographe(nom, prenom, pseudo, email, password);
		return this.photographeRepo.save(photographe);
	}

}
