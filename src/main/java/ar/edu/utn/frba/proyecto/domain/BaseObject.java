package ar.edu.utn.frba.proyecto.domain;

import java.io.Serializable;

public abstract class BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4923692171618250892L;

	protected Integer id;
	
	protected String nombre;
	
	public abstract String getIdentifingName();
	
	public Integer getStateGroupId(){
		//By default, return nothing. Only to be implemented for those who have state.
		return null;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}