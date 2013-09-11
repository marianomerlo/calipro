package ar.edu.utn.frba.proyecto.domain;

public class Banda extends AuditObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 594967867634434295L;

	@Override
	public String getIdentifingName() {
		return this.nombre;
	}

}
