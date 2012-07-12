package ar.edu.utn.frba.proyecto.dao;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.utn.frba.proyecto.domain.User;

/**
 * 
 * @author mariano.gonzalez@mulesoft.com
 *
 */
@Repository
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {

	/**
	 * @see org.mule.sfdc2boxnet.dao.UserDao#getByUsername(java.lang.String)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public User getByUsername(String username) {
		return (User) this.createCriteria().add(Restrictions.eq("username", username)).uniqueResult();
	}

}
