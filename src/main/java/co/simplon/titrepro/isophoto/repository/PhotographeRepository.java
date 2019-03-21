package co.simplon.titrepro.isophoto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import co.simplon.titrepro.isophoto.model.Photographe;

public interface PhotographeRepository extends JpaRepository<Photographe, String> {

	Optional<Photographe> findByNom(String nom);

}
