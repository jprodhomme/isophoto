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

	public PhotoServiceImpl(PhotoRepository photoRepo, 
							PhotographeRepository photographeRepo, 
							TagRepository tagRepo) {
		this.photoRepo = photoRepo;
		this.photographeRepo = photographeRepo;
		this.tagRepo = tagRepo;

	}

	@Override
	public Photo savePhoto(String description, 
						   String titre, 
						   String image, 
						   String tagsString, 
						   String pseudo) {

		ArrayList<Tag> tags = new ArrayList<Tag>();

		for (String tagString : tagsString.split(",")) {

			Tag tag = this.tagRepo.findByTag(tagString);
			
			if (tag == null) {
				tag = this.tagRepo.save(new Tag(tagString));
			}
			tags.add(tag);

		}

		Photo photo = new Photo(description, titre, image, tags, this.photographeRepo.findByPseudo(pseudo));

		this.photoRepo.save(photo);

		return photo;

	}
	
	@Override
    public Photo addPhoto(Photo photo, String pseudo) {
 
        
            Photo photoToSave = new Photo(photo.getDescription(),
            						  	  photo.getTitre(),
            						  	  photo.getImage(),
            							  (ArrayList<Tag>) photo.getTags(),
            							  this.photographeRepo.findByPseudo(pseudo));

            photoRepo.save(photoToSave);

            return photoToSave;
       
    }
	
}
