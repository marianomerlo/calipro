package ar.edu.utn.frba.proyecto.dao;

import java.io.Serializable;
import java.util.List;

public interface AbmDao<T extends Serializable> extends Dao<T> {

	public T getByUnique(T element);
	
	public void add(T element);
	
	public void update(T element);
	
	public void delete(List<T> elements);
	
	public void deleteAll();
	
}
