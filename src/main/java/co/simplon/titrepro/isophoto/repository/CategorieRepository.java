package co.simplon.titrepro.isophoto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.simplon.titrepro.isophoto.model.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, String> {
	
	@Query("SELECT g FROM Categorie g WHERE g.id LIKE %?1%")
	  public Categorie findByCategorieId(int id);

	public Optional<Categorie> findByNom(String nom);
	


}


