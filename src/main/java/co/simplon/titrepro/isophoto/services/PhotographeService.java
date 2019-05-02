package co.simplon.titrepro.isophoto.services;

import co.simplon.titrepro.isophoto.model.Photographe;

public interface PhotographeService {
	
	Photographe findByPseudo(String pseudo);

	public Photographe savePhotographe(String nom, 
									   String prenom, 
									   String pseudo, 
									   String email, 
									   String password);
	
}
