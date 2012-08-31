package ar.edu.utn.frba.proyecto.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import ar.edu.utn.frba.proyecto.dao.Dao;
import ar.edu.utn.frba.proyecto.dao.impl.GenericDao;

public abstract class BaseController<T extends Serializable> implements Serializable {

	/**
	 * 
	 */
	protected static final long serialVersionUID = 4410085728682028191L;
	
	protected String ITEM_NAME;
	
	protected List<T> items;
	
	@ManagedProperty("#{genericDao}")
	protected GenericDao genericDao;
	
	protected abstract Dao<T> getDao();
	
	public List<T> getItems() {
		if (this.items == null)
			this.items = getDao().getAll();

		return this.items;
	}
	
	public String getITEM_NAME() {
		return ITEM_NAME;
	}

	public void setITEM_NAME(String iTEM_NAME) {
		ITEM_NAME = iTEM_NAME;
	}
	
	public void setItems(List<T> items) {
		this.items = items;
	}

	public GenericDao getGenericDao() {
		return genericDao;
	}

	public void setGenericDao(GenericDao genericDao) {
		this.genericDao = genericDao;
	}

}
