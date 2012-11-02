package ar.edu.utn.frba.proyecto.domain;


public class Estado extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5750833109871284829L;
	
	public Estado(){
		super();
	}
	
	public Estado(Integer id, String nombre){
		super();
		this.id = id;
		this.nombre = nombre;
	}
	
	@Override
	public String getIdentifingName() {
		return getNombre();
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
}
