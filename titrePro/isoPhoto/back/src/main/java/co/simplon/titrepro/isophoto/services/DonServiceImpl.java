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

		Photo photo = photoRepo.findById(idPhoto).get();
		
		Date date = new Date();
		
		Long time = date.getTime()+7200000; // pour éviter le décalage de 2h
		  
		Timestamp ts = new Timestamp(time);
		
		Don don = new Don(commentaire, ts, montant);

		List<Don> dons = photo.getDons();

		dons.add(don);

		don.setPhoto(photo);

		this.donRepo.save(don);

		this.photoRepo.save(photo);

		return photo;
	}

}
