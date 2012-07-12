package ar.edu.utn.frba.proyecto.dao;

import ar.edu.utn.frba.proyecto.domain.User;


/**
 * 
 * @author mariano.gonzalez@mulesoft.com
 *
 */
public interface UserDao extends Dao<Long, User> {

	public User getByUsername(String username);
	
}
