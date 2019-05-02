package co.simplon.titrepro.isophoto.services;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import co.simplon.titrepro.isophoto.model.Don;
import co.simplon.titrepro.isophoto.model.Photo;
import co.simplon.titrepro.isophoto.repository.DonRepository;
import co.simplon.titrepro.isophoto.repository.PhotoRepository;

@Service
public class DonServiceImpl implements DonService {

	private DonRepository donRepo;
	private PhotoRepository photoRepo;
	
	public DonServiceImpl(DonRepository donRepo,
						  PhotoRepository photoRepo) {
		this.donRepo = donRepo;
		this.photoRepo = photoRepo;
	}

	@Override
	public Photo addDon(Long idPhoto, 
						String commentaire,		
						
						Integer montant) {
		
		
		Photo photo = photoRepo.findById(idPhoto).get();//récup la photo avec son id
		
		// Définir la date du don avec le timestamp actuel
		Date date = new Date();
		Long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		
		System.out.println("Timestamp =====> " + ts);
		
		Don don = new Don(commentaire, ts,  montant);
		
		
		List<Don> dons = photo.getDons(); //récup dons de la photo
		
		dons.add(don); //on add le nouveau don
	
		
		
		don.setPhoto(photo);
		
		
		
		this.donRepo.save(don);
				
		this.photoRepo.save(photo);
		
		return photo;
	}
	

	
}
