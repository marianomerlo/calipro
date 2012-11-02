package ar.edu.utn.frba.proyecto.domain;


import java.util.Arrays;
import java.util.List;

import ar.edu.utn.frba.proyecto.helper.ProfileHelper;

public class Profile extends BaseObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2030356771612881776L;

	public Profile(){
		super();
	}
	
	public Profile ( Integer id, String nombre){
		super();
		this.id = id;
		this.nombre = nombre;
		this.vistas = ProfileHelper.getViews(this);
	}

	public Profile ( String nombre, Vista... vistas){
		super();
		this.nombre = nombre;
		this.vistas = vistas != null ? Arrays.asList(vistas) : null;
	}

	private List<Vista> vistas;
	
	/**
	 * @return the vistas
	 */
	public List<Vista> getVistas() {
		return vistas;
	}

	/**
	 * @param vistas the vistas to set
	 */
	public void setVistas(List<Vista> vistas) {
		this.vistas = vistas;
	}

	@Override
	public String getIdentifingName() {
		return this.nombre;
	}
	
	@Override
	public int hashCode() {
		return ( nombre.length() * 31 ) ^ id;
	}	
	
	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof Profile) {
		      Profile other = (Profile) arg0;
		      return (nombre.equals(other.nombre) && id == other.id);
		    }
		    return false;
	}
}
