package ar.edu.utn.frba.proyecto.domain;

import java.io.Serializable;

public class BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4923692171618250892L;

	protected Integer id;

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
}
