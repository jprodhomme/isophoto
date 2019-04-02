package co.simplon.titrepro.isophoto.api.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.fasterxml.jackson.annotation.JsonIgnore;

import co.simplon.titrepro.isophoto.model.Categorie;
import co.simplon.titrepro.isophoto.repository.AuthorityRepository;
import co.simplon.titrepro.isophoto.repository.CategorieRepository;
import co.simplon.titrepro.isophoto.repository.ExifRepository;
import co.simplon.titrepro.isophoto.repository.PhotoRepository;
import co.simplon.titrepro.isophoto.repository.PhotographeRepository;

@RestController
@RequestMapping("/api")
public class CategorieController {

	@Autowired
	PhotoRepository photoRepo;

	@Autowired
	CategorieRepository catRepo;

	@Autowired
	PhotographeRepository photographeRepo;

	@Autowired
	ExifRepository exifRepo;

	@Autowired
	AuthorityRepository authorityRepo;

	/**
	 * Méthode POST pour ajouter une CATEGORIE
	 * 
	 * @param id
	 * @param nom
	 * @return
	 */
	@PostMapping("/addcategorie")
	public ResponseEntity<?> addCategorie(@Valid String nom) {

		Categorie categorie = new Categorie(nom);
		catRepo.save(categorie);

		try {
			// authoritiesRepo.save(authorities);
			return ResponseEntity.status(HttpStatus.OK).body(null);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}
	/*
	 * TEST METADATA EXTRACTOR ====> A METTRE EN JSON <====== A TEST SUR PHOTO RAW
	 * ------> OK A TEST SUR JPEG -------> INFOS USELESS A TEST SUR RAW transfo en
	 * JPEG --> INFOS OK A METTRE DANS UN EXIF CONTROLLER/SERVICE
	 */
	@JsonIgnore
	@GetMapping("/exif")
	public void getCategorie() throws ImageProcessingException, IOException {

		File nefFile = new File("/home/julien/Bureau/test.nef");
		
//		List<File> file = new ArrayList<File>();
		

		Metadata metadata = ImageMetadataReader.readMetadata(nefFile);
		Iterable<Directory> directories = metadata.getDirectories();
		Iterator<Directory> iterator = directories.iterator();
		
		while(iterator.hasNext()) {
			Directory dir = iterator.next();
			Collection<Tag> tags = dir.getTags();
			for(Tag tag:tags) {
				System.out.println(tag.getTagName() + "  " + tag.getDescription() + "  " + tag.getTagTypeHex());
				
			}
			
		}
	
//		for (Directory directory : metadata.getDirectories()) {
//			for ( Tag tag : directory.getTags()) {
//				System.out.format("TEST " + "[%s] - %s = %s", directory.getName(), tag.getTagName(),
//						tag.getDescription());
//				System.out.println(s);
//			}
//			if (directory.hasErrors()) {
//				for (String error : directory.getErrors()) {
//					System.err.format("ERROR: %s", error);
//				}
//			}
//		}
	}
}
