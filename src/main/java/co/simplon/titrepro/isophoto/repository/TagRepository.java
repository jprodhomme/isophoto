package co.simplon.titrepro.isophoto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.simplon.titrepro.isophoto.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
	
	@Query("SELECT t FROM Tag t WHERE t.id LIKE ?1")
	  public Tag findByTagId(Integer id);

	public Optional<Tag> findByTag(String tag);
	


}


