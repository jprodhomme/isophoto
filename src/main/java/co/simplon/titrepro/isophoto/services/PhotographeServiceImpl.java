package co.simplon.titrepro.isophoto.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import co.simplon.titrepro.isophoto.model.Photographe;
import co.simplon.titrepro.isophoto.repository.PhotographeRepository;

@Service
public class PhotographeServiceImpl implements PhotographeService{
	
	private PhotographeRepository photographeRepo;
	
	
	
	public PhotographeServiceImpl(PhotographeRepository photographeRepo) {
		this.photographeRepo = photographeRepo;
	
	}
	
	@Override 
	public Photographe findByPseudo(String pseudo) {
		return  this.photographeRepo.findByPseudo(pseudo);
		
		
	}
}
