package ar.edu.utn.frba.proyecto.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author mariano.gonzalez@mulesoft.com
 *
 * @param <K>
 * @param <T>
 */
public abstract class AbstractDao<K extends Serializable, T> implements Dao<K, T> {

	private SessionFactory sessionFactory;
	private Class<T> persistentType;
	
	@SuppressWarnings("unchecked")
	public AbstractDao() {
		ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.persistentType = (Class<T>) parameterizedType.getActualTypeArguments()[1];
	}
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED)
	public List<T> findAll() {
		return this.getSession().createCriteria(this.persistentType).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED)
	public T get(K key) {
		return (T) this.getSession().get(this.persistentType, key);
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED)
	public T load(K key) {
		return (T) this.getSession().load(this.persistentType, key);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(T object) {
		this.getSession().save(object);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(T object) {
		this.getSession().update(object);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(T object) {
		this.getSession().delete(object);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void lock(T object, LockMode mode) {
		this.lock(object, mode);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void refresh(T object) {
		this.getSession().refresh(object);
	};
	
	@Override
	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED)
	public T merge(T object) {
		return (T) this.getSession().merge(object);
	}
	
	@Override
	public void dettach(T object) {
		this.getSession().evict(object);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean exists(K key) {
		return key == null ? false : this.getSession().get(this.persistentType, key) != null;
	};
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean contains(T object) {
		return this.getSession().contains(object);
	};
	
	protected Criteria createCriteria() {
		return this.getSession().createCriteria(this.persistentType);
	}
	
	protected Class<T> getPersistentType() {
		return this.persistentType;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
