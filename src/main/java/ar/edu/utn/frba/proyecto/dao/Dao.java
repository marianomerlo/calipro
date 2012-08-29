package ar.edu.utn.frba.proyecto.dao;

import java.io.Serializable;
import java.util.List;

public interface Dao<T extends Serializable> {

	public T get(T element);
	
	public List<T> getAll();
	
	public T getByUnique(T element);
	
	public void add(T element);
	
	public void update(T element);
	
	public void delete(List<T> elements);
	
	public void deleteAll();
	
}
