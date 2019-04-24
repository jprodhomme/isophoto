package co.simplon.titrepro.isophoto.services;

import org.springframework.data.jpa.repository.Query;

import co.simplon.titrepro.isophoto.model.Photographe;

public interface PhotographeService {
	@Query("SELECT p FROM Photographe p WHERE p.pseudo LIKE ?1")
	Photographe findByPseudo(String pseudo);

//	public Photographe findByPseudo(String pseudo);
	

}
