package ar.edu.utn.frba.proyecto.service;

import ar.edu.utn.frba.proyecto.dao.back.UserDao;
import ar.edu.utn.frba.proyecto.domain.LoginException;
import ar.edu.utn.frba.proyecto.domain.User;


/**
 * 
 * @author mariano.gonzalez@mulesoft.com
 *
 */
public class UserServiceImpl implements UserService {

	private static final long serialVersionUID = 2579218322522491080L;
	private UserDao userDao;
	
	/**
	 * @see org.mule.sfdc2boxnet.service.UserService#login(java.lang.String, java.lang.String)
	 */
	@Override
	public User login(String username, String password) throws LoginException {
		return new User();
	}
	
	private void throwLoginException() throws LoginException {
		final String msg = "Invalid Username/Password validating login";
		throw new LoginException(msg);
	}
	
	

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
}
