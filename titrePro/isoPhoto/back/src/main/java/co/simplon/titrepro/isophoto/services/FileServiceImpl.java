package co.simplon.titrepro.isophoto.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {
	private final Path rootLocation = Paths.get("/home/julien/Documents/programmation/isophoto/titrePro/isoPhoto/front/isoPhotoFront/src/assets/uploadedPhotos");

	public void store(MultipartFile file) {
		try {
			System.out.println(file.getOriginalFilename());
			System.out.println(rootLocation.toUri());
			
			String image = rootLocation.toUri() +file.getOriginalFilename();
			System.out.println(image);
			Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
		} catch (Exception e) {
			throw new RuntimeException("FAIL!");
		}
	}
	public String photoString(MultipartFile file) {
		try {
			System.out.println(file.getOriginalFilename());
			System.out.println(rootLocation.toUri());
			
			String image = rootLocation.toUri() +file.getOriginalFilename();
			System.out.println(image);
			return image;
			
		} catch (Exception e) {
			throw new RuntimeException("FAIL!");
		}
		
	}
}
