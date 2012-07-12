package ar.edu.utn.frba.proyecto.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Session;

/**
 * 
 * @author mariano.gonzalez@mulesoft.com
 *
 * @param <K>
 * @param <T>
 */
public interface Dao<K extends Serializable, T> {

	public List<T> findAll();
	
	public T get(K key);
	
	public T load(K key);
	
	public void save(T object);
	
	public void delete(T object);
	
	public void lock(T object, LockMode mode);
	
	public void refresh(T object);
	
	public Session getSession();
	
	public T merge(T object);
	
	public boolean contains(T object);
	
	public boolean exists(K key);
	
	public void dettach(T object);
	
	public void update(T object);
	
}
