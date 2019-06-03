
package co.simplon.titrepro.isophoto.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {
	private final Path rootLocation = Paths.get("/home/julien/Documents/programmation/isophoto/titrePro/isoPhoto/front/isoPhotoFront/src/assets/uploadedPhotos");

	public void store(MultipartFile file, String pseudo) {
		try {
			
			Files.copy(file.getInputStream(), this.rootLocation.resolve(pseudo +file.getOriginalFilename()));
		} catch (Exception e) {
			throw new RuntimeException("FAIL!");
		}
	}
	

}