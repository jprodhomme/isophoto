package co.simplon.titrepro.isophoto.services;

import java.util.List;
import java.util.Optional;

import co.simplon.titrepro.isophoto.exception.ExistingUsernameException;
import co.simplon.titrepro.isophoto.exception.InvalidCredentialsException;
import co.simplon.titrepro.isophoto.model.Photographe;

public interface PhotographeService {
	
	public Photographe findByPseudo(String pseudo);

	public Photographe savePhotographe(String nom, 
									   String prenom, 
									   String pseudo, 
									   String email, 
									   String password);
	  /**
     * Method that signs a user in the application.
     * @param username the user username.
     * @param password the user password.
     * @return the JWT if credentials are valid, throws InvalidCredentialsException otherwise.
     * @throws InvalidCredentialsException
     */
    String signin(String pseudo, String password) throws InvalidCredentialsException;

    /**
     * Method that signs up a new user in the application.
     * @param user the new user to create.
     * @return the JWT if user username is not already existing.
     * @throws ExistingUsernameException
     */
    String signup(Photographe photographe) throws ExistingUsernameException;
	
    List<Photographe> findAllPhotographe();
}
