package co.simplon.titrepro.isophoto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.simplon.titrepro.isophoto.model.Exif;

public interface ExifRepository extends JpaRepository<Exif, String> {

		@Query("SELECT objectif FROM Exif")
		public void findAllObjectif();
		
	
}
