package ar.edu.utn.frba.proyecto.dao.zback;

import ar.edu.utn.frba.proyecto.domain.User;


/**
 * 
 * @author mariano.gonzalez@mulesoft.com
 *
 */
public interface UserDao extends DaoBACK<Long, User> {

	public User getByUsername(String username);
	
}
