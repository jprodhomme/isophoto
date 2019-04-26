package co.simplon.titrepro.isophoto.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.simplon.titrepro.isophoto.model.Don;
import co.simplon.titrepro.isophoto.model.Photo;
import co.simplon.titrepro.isophoto.repository.DonRepository;
import co.simplon.titrepro.isophoto.repository.PhotoRepository;

@Service
public class DonServiceImpl implements DonService {

	@Override
	public Photo addDon(long idPhoto, Don don) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	private DonRepository donRepo;
//	
//	private PhotoRepository photoRepo;
//	
//	public DonServiceImpl(DonRepository donRepo,
//						  PhotoRepository photoRepo) {
//		this.donRepo = donRepo;
//		this.photoRepo = photoRepo;
//		
//	}
//	
//	@Override
//	public Photo addDon(long idPhoto, String don) {
//		
//		Photo photo = photoRepo.findById(idPhoto).get();
//		List<Don> dons = photo.getDons();
//		
//		Don don = new Don ()
//		return null;
//		
//		
	
}
