package co.simplon.titrepro.isophoto.services;

import org.springframework.stereotype.Service;
import co.simplon.titrepro.isophoto.model.Tag;
import co.simplon.titrepro.isophoto.repository.TagRepository;

@Service
public class TagServiceImpl implements TagService {

	private TagRepository tagRepo;

	public TagServiceImpl(TagRepository tagRepo) {
		this.tagRepo = tagRepo;
	}

	@Override
	public Tag saveTag(String tagString) {

		Tag tag = new Tag(tagString);
		return this.tagRepo.save(tag);
	}

	@Override
	public Tag findByTag(String tag) {
		return this.tagRepo.findByTag(tag);
	}
	
	

}
