package co.simplon.titrepro.isophoto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.simplon.titrepro.isophoto.model.Photographe;

public interface PhotographeRepository extends JpaRepository<Photographe, Long> {

	Photographe findByPseudo(String pseudo);
	
	@Query("SELECT p FROM Photographe p WHERE p.pseudo LIKE ?1")
	Optional <Photographe> findByPseudo2(String pseudo);

	boolean existsByPseudo(String pseudo);

    void deleteByPseudo(String pseudo);

}
