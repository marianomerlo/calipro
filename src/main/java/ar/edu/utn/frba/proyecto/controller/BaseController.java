package ar.edu.utn.frba.proyecto.controller;

import java.io.Serializable;
import java.util.List;

import ar.edu.utn.frba.proyecto.dao.Dao;
import ar.edu.utn.frba.proyecto.domain.BaseObject;

public abstract class BaseController<T extends BaseObject> implements Serializable {

	/**
	 * 
	 */
	protected static final long serialVersionUID = 4410085728682028191L;
	
	protected String ITEM_NAME;

	protected int autoUpdateFrequencySeconds;
	
	protected List<T> items;
	
	protected abstract Dao<T> getDao();
	
	public List<T> getItems() {
		if (this.items == null ) {
			this.items = getDao().getAll();
			extraGetItemsProcess();
		}

		return this.items;
	}
	
	public T get(T element) {
		element = getDao().get(element);
		extraGetItemProcess( element );

		return element;
	}
	
	protected void extraGetItemProcess( T element ) {
		// By default, do nothing.
	}

	protected void extraGetItemsProcess() {
		for (T elem : getItems()) {
			extraGetItemProcess(elem);
		}
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

	public int getAutoUpdateFrequencySeconds() {
		return autoUpdateFrequencySeconds;
	}

	public void setAutoUpdateFrequencySeconds(int autoUpdateFrequencySeconds) {
		this.autoUpdateFrequencySeconds = autoUpdateFrequencySeconds;
	}
}
