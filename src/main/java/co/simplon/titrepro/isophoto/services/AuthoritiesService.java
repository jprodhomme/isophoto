package co.simplon.titrepro.isophoto.services;

import java.util.List;

import co.simplon.titrepro.isophoto.model.Authority;

public interface AuthoritiesService {
	
	public List<Authority> getAllAuthorities();
	
	public Authority saveAuthorities(Authority authorities);
	
	public void deleteAuthorities(Authority authorities);


}

