package co.simplon.titrepro.isophoto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.simplon.titrepro.isophoto.model.Commentaire;

public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {
	
	
}
