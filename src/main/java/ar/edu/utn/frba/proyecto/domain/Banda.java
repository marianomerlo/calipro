package ar.edu.utn.frba.proyecto.domain;


public class Banda extends AuditObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 594967867634434295L;
	
	private String tiempoAsignado;
	
	private Double costoExtra;

	@Override
	public String getIdentifingName() {
		return this.nombre;
	}
	
	public Banda(){
		super();
	}
	
	public Banda(Integer id, String nombre, String tiempoAsignado, String costoExtra){
		super();
		this.id = id;
		this.nombre = nombre;
		this.tiempoAsignado = tiempoAsignado;
		this.costoExtra = costoExtra != null ? new Double(costoExtra) : null;
	}

	public Banda(Banda element){
		super();
		this.id = element.getId();
		this.nombre = element.getNombre();
		this.tiempoAsignado = element.getTiempoAsignado();
		this.costoExtra = element.getCostoExtra();
	}

	/**
	 * @return the tiempoAsignado
	 */
	public String getTiempoAsignado() {
		return tiempoAsignado;
	}

	/**
	 * @param tiempoAsignado the tiempoAsignado to set
	 */
	public void setTiempoAsignado(String tiempoAsignado) {
		this.tiempoAsignado = tiempoAsignado;
	}

	/**
	 * @return the costoExtra
	 */
	public Double getCostoExtra() {
		return costoExtra;
	}

	/**
	 * @param costoExtra the costoExtra to set
	 */
	public void setCostoExtra(Double costoExtra) {
		this.costoExtra = costoExtra;
	}
	
	@Override
	public int hashCode() {
		return ( nombre.length() * 31 ) ^ id;
	}	
	
	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof Banda) {
			Banda other = (Banda) arg0;
		      return (nombre.equals(other.nombre) && id == other.id);
		    }
		    return false;
	}

}
