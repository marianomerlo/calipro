package ar.edu.utn.frba.proyecto.domain;

import java.io.Serializable;

public class Analisis extends AuditObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1548694953389239757L;

	private String nombre;

	public Analisis ( Integer analisisId, String nombre){
		super();
		this.id = analisisId;
		this.nombre = nombre;
	}
	
	public Analisis ( Analisis analisis){
		super();
		this.id = analisis.getId();
		this.nombre = analisis.getNombre();
	}
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String getIdentifingName() {
		return getNombre();
	}

}
