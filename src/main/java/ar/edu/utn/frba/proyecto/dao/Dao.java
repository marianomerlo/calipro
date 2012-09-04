package ar.edu.utn.frba.proyecto.dao;

import java.io.Serializable;
import java.util.List;

public interface Dao<T extends Serializable> extends Serializable {

	public T get(T element);

	public List<T> getAll();

}
