package ar.edu.utn.frba.proyecto.domain;

import java.io.Serializable;

public class Analisis extends AuditObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1548694953389239757L;

	private String analisisId;
	
	private String nombre;

	public Analisis ( String analisisId, String nombre){
		super();
		this.analisisId = analisisId;
		this.nombre = nombre;
	}
	
	public Analisis ( Analisis analisis){
		super();
		this.analisisId = analisis.getAnalisisId();
		this.nombre = analisis.getNombre();
	}
	
	public String getAnalisisId() {
		return analisisId;
	}

	public void setAnalisisId(String analisisId) {
		this.analisisId = analisisId;
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

	
	
}
