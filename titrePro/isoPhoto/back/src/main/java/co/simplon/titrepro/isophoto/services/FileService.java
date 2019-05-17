package co.simplon.titrepro.isophoto.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	void store(MultipartFile file);
	
	String photoString(MultipartFile file);
	

}
