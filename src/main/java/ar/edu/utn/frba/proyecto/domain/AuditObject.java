package ar.edu.utn.frba.proyecto.domain;



public abstract class AuditObject extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -168794914295343374L;

	protected Usuario usuarioCreacion;
	
	protected String fechaCreacion;
	
	protected Usuario usuarioUltimaModificacion;
	
	protected String fechaUltimaModificacion;

	public Usuario getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(Usuario usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Usuario getUsuarioUltimaModificacion() {
		return usuarioUltimaModificacion;
	}

	public void setUsuarioUltimaModificacion(Usuario usuarioUltimaModificacion) {
		this.usuarioUltimaModificacion = usuarioUltimaModificacion;
	}

	public String getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	public void setFechaUltimaModificacion(String fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}
}
