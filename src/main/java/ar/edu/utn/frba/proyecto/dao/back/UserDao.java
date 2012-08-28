package ar.edu.utn.frba.proyecto.dao.back;

import ar.edu.utn.frba.proyecto.domain.User;


/**
 * 
 * @author mariano.gonzalez@mulesoft.com
 *
 */
public interface UserDao extends DaoBACK<Long, User> {

	public User getByUsername(String username);
	
}
