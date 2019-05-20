package co.simplon.titrepro.isophoto.services;

import co.simplon.titrepro.isophoto.exception.ExistingUsernameException;
import co.simplon.titrepro.isophoto.exception.InvalidCredentialsException;
import co.simplon.titrepro.isophoto.model.Photographe;
import co.simplon.titrepro.isophoto.repository.PhotographeRepository;
import co.simplon.titrepro.isophoto.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PhotographeServiceImpl implements PhotographeService {

	private PhotographeRepository photographeRepo;
	private BCryptPasswordEncoder passwordEncoder;
	private JwtTokenProvider jwtTokenProvider;
	private AuthenticationManager authenticationManager;


	public PhotographeServiceImpl(PhotographeRepository photographeRepo, 
								  BCryptPasswordEncoder passwordEncoder,
								  JwtTokenProvider jwtTokenProvider,
								  AuthenticationManager authenticationManager) {

		this.photographeRepo = photographeRepo;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
		this.authenticationManager = authenticationManager;
	}
	
	
	
	
	
    @Override
    public String signin(String pseudo, String password) throws InvalidCredentialsException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(pseudo, password));
            return jwtTokenProvider.createToken(pseudo, photographeRepo.findByPseudo(pseudo).getAuthority());
        } catch (AuthenticationException e) {
            throw new InvalidCredentialsException();
        }
    }
    
    @Override
    public String signup(Photographe photographe) throws ExistingUsernameException {
        if (!photographeRepo.existsByPseudo(photographe.getPseudo())) {
            Photographe photographeToSave = new Photographe(photographe.getNom(),
            												photographe.getPrenom(),
            												photographe.getPseudo(),
            												photographe.getEmail(),
            												passwordEncoder.encode(photographe.getPassword()), 
            												photographe.getAuthority());
            photographeRepo.save(photographeToSave);
            return jwtTokenProvider.createToken(photographe.getPseudo(),
            									photographe.getAuthority());
        } else {
            throw new ExistingUsernameException();
        }
    }

	@Override
	public Photographe findByPseudo(String pseudo) {
		return this.photographeRepo.findByPseudo(pseudo);

	}

	@Override
	public Photographe savePhotographe(String nom, 
			   						   String prenom, 
			   						   String pseudo, 
			   						   String email, 
			   						   String password) {

		Photographe photographe = new Photographe(nom, prenom, pseudo, email, password);
		return this.photographeRepo.save(photographe);
	}
	
    /**
     * Method that finds all users from the application database.
     * @return the list of all application users.
     */
	@Override
    public List<Photographe> findAllPhotographe(){
    	return this.photographeRepo.findAll();
    }

}
