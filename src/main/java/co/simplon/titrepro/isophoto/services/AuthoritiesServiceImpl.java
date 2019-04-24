package co.simplon.titrepro.isophoto.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.simplon.titrepro.isophoto.model.Authority;
import co.simplon.titrepro.isophoto.repository.AuthorityRepository;

@Service
public class AuthoritiesServiceImpl  implements AuthoritiesService {
	private AuthorityRepository authoritiesRepo;

    public AuthoritiesServiceImpl(AuthorityRepository authoritiesRepository) {
        this.authoritiesRepo = authoritiesRepository;
    }


    public List<Authority> getAllAuthorities() {
        // TODO Auto-generated method stub
        return this.authoritiesRepo.findAll();
    }


	@Override
	public Authority saveAuthorities(Authority authorities) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void deleteAuthorities(Authority authorities) {
		// TODO Auto-generated method stub
		
	}
    
//    public Authority saveAuthorities(Authority authorities) {
//    	System.out.println("authorities ");
//    	System.out.println("authorities " + authorities.getAuthority());
//    	System.out.println("authorities " + authorities.getAuthority() + " " + authorities.getPhotographes().get(index));
//		return this.authoritiesRepo.save(authorities);
//	}
//    
//    public void deleteAuthorities(Authority authorities) {
//    	this.authoritiesRepo.deleteByUsername(authorities.getUtilisateurs().getUsername());
//    }
}
