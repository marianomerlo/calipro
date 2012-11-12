package ar.edu.utn.frba.proyecto.domain;

import java.io.Serializable;
import java.util.List;

public class Analisis extends AuditObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1548694953389239757L;
	
	private List<Criterio> criterios;
	
	private Estado estado;

	public Analisis ( Integer analisisId, String nombre ){
		super();
		this.id = analisisId;
		this.nombre = nombre;
	}
	
	public Analisis ( Analisis analisis ){
		super();
		this.id = analisis.getId();
		this.nombre = analisis.getNombre();
		this.criterios = analisis.getCriterios();
	}
	
	public Analisis(){
		super();
	}
	
	@Override
	public String getIdentifingName() {
		return getNombre();
	}

	/**
	 * @return the criterios
	 */
	public List<Criterio> getCriterios() {
		return criterios;
	}

	/**
	 * @param criterios the criterios to set
	 */
	public void setCriterios(List<Criterio> criterios) {
		this.criterios = criterios;
	}
	
	@Override
	public int hashCode() {
		return ( nombre.length() * 31 ) ^ id;
	}	
	
	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof Estado) {
			Estado other = (Estado) arg0;
		      return (nombre.equals(other.nombre) && id == other.id);
		    }
		    return false;
	}

	/**
	 * @return the estado
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}
