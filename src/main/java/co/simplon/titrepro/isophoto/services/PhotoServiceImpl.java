package co.simplon.titrepro.isophoto.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import co.simplon.titrepro.isophoto.model.Photo;
import co.simplon.titrepro.isophoto.model.Tag;
import co.simplon.titrepro.isophoto.repository.PhotoRepository;
import co.simplon.titrepro.isophoto.repository.PhotographeRepository;
import co.simplon.titrepro.isophoto.repository.TagRepository;

@Service
public class PhotoServiceImpl implements PhotoService {

	private PhotoRepository photoRepo;
	private PhotographeRepository photographeRepo;
	private TagRepository tagRepo;

	public PhotoServiceImpl(PhotoRepository photoRepo, PhotographeRepository photographeRepo, TagRepository tagRepo) {
		this.photoRepo = photoRepo;
		this.photographeRepo = photographeRepo;
		this.tagRepo = tagRepo;
	}

	@Override
	public Photo savePhoto(String description, 
						   String titre, 
						   String image, 
						   ArrayList<String> tagsString,
						   String pseudo) {

		ArrayList<Tag> tags = new ArrayList<Tag>();
		for (String tagString : tagsString) {
			tags.add(new Tag(tagString));
			
			
		}

		Photo photo = new Photo(description, titre, image, tags, this.photographeRepo.findByPseudo(pseudo));
		this.tagRepo.saveAll(tags);
		return this.photoRepo.save(photo);
		
	}
}
