package co.simplon.titrepro.isophoto.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import co.simplon.titrepro.isophoto.model.Commentaire;
import co.simplon.titrepro.isophoto.model.Photo;
import co.simplon.titrepro.isophoto.model.Tag;
import co.simplon.titrepro.isophoto.repository.CommentaireRepository;
import co.simplon.titrepro.isophoto.repository.PhotoRepository;
import co.simplon.titrepro.isophoto.repository.PhotographeRepository;
import co.simplon.titrepro.isophoto.repository.TagRepository;

@Service
public class PhotoServiceImpl implements PhotoService {

	private PhotoRepository photoRepo;
	private PhotographeRepository photographeRepo;
	private TagRepository tagRepo;
	private CommentaireRepository commentaireRepo;

	public PhotoServiceImpl(PhotoRepository photoRepo, PhotographeRepository photographeRepo, TagRepository tagRepo, CommentaireRepository commentaireRepo) {
		this.photoRepo = photoRepo;
		this.photographeRepo = photographeRepo;
		this.tagRepo = tagRepo;
		this.commentaireRepo = commentaireRepo;
	}

	@Override
	public Photo savePhoto(String description, String titre, String image, String tagsString, String pseudo) {

		ArrayList<Tag> tags = new ArrayList<Tag>();

		for (String tagString : tagsString.split(",")) {

			Tag tag = this.tagRepo.findByTag(tagString);
			if (tag == null) {

				tag = this.tagRepo.save(new Tag(tagString));
			}
			tags.add(tag);

		}

		Photo photo = new Photo(description, titre, image,tags, this.photographeRepo.findByPseudo(pseudo));

		this.photoRepo.save(photo);

		
		return photo;
		
	}
	
	
}
