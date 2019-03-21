package co.simplon.titrepro.isophoto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.simplon.titrepro.isophoto.model.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, String> {
	
	@Query("SELECT g FROM Categorie g WHERE g.id LIKE %?1%")
	  public Categorie findByCategorie(int id);
	
	public Categorie findById(int id);

}


