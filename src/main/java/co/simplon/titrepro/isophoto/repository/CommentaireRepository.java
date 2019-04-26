package co.simplon.titrepro.isophoto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.simplon.titrepro.isophoto.model.Commentaire;

public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {
	
	@Query("SELECT t FROM Tag t WHERE t.tag LIKE ?1")
	public Commentaire findByCommentaire(String commentaire);

}
