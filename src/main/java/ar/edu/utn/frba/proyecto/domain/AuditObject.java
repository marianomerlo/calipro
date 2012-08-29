package ar.edu.utn.frba.proyecto.domain;

import java.io.Serializable;


public abstract class AuditObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -168794914295343374L;

	protected Integer id;

	protected String usuarioCreacion;
	
	protected String fechaCreacion;
	
	protected String usuarioUltimaModificacion;
	
	protected String fechaUltimaModificacion;

	public abstract String getIdentifingName();
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getUsuarioUltimaModificacion() {
		return usuarioUltimaModificacion;
	}

	public void setUsuarioUltimaModificacion(String usuarioUltimaModificacion) {
		this.usuarioUltimaModificacion = usuarioUltimaModificacion;
	}

	public String getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	public void setFechaUltimaModificacion(String fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}
}
