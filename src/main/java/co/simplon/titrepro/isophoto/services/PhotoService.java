package co.simplon.titrepro.isophoto.services;

import java.util.ArrayList;

import co.simplon.titrepro.isophoto.model.Photo;

public interface PhotoService {
	
	public Photo savePhoto( String description, 
						    String titre,  
						    String image, 
						    String tagsString, 
						    String pseudo);


}
