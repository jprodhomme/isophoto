package co.simplon.titrepro.isophoto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.titrepro.isophoto.model.Authority;
import co.simplon.titrepro.isophoto.model.Photographe;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
	
	Authority findByRole(String role);

}
