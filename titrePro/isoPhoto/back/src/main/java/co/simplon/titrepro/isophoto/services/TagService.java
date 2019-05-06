package co.simplon.titrepro.isophoto.services;

import co.simplon.titrepro.isophoto.model.Tag;

public interface TagService {
	
	Tag findByTag(String tag);

	Tag saveTag(String tag);

}
