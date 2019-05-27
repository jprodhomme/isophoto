package co.simplon.titrepro.isophoto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.titrepro.isophoto.model.Photographe;

public interface PhotographeRepository extends JpaRepository<Photographe, Long> {

	Photographe findByPseudo(String pseudo);
	
	boolean existsByPseudo(String pseudo);

    void deleteByPseudo(String pseudo);

}
