package ar.edu.utn.frba.proyecto.service;


import java.io.Serializable;

import ar.edu.utn.frba.proyecto.domain.LoginException;
import ar.edu.utn.frba.proyecto.domain.User;


/**
 * 
 * @author mariano.gonzalez@mulesoft.com
 *
 */
public interface UserService extends Serializable {

	public User login(String username, String password) throws LoginException;
	
}
