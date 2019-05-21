package co.simplon.titrepro.isophoto.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.simplon.titrepro.isophoto.model.Photographe;
import co.simplon.titrepro.isophoto.repository.PhotographeRepository;



@Service
public class PhotographeDetailsService implements UserDetailsService {

    @Autowired
    private PhotographeRepository photographeRepo;

    @Override
    public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {
        final Photographe photographe = photographeRepo.findByPseudo(pseudo);

        if (photographe == null) {
            throw new UsernameNotFoundException("AppUser '" + pseudo + "' not found");
        }

        return User
                .withUsername(pseudo)
                .password(photographe.getPassword())
                .authorities(photographe.getAuthority().getRole())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
	
}
