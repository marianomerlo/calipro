package ar.edu.utn.frba.proyecto.domain;

public class Reporte extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1258206211695741666L;

	@Override
	public String getIdentifingName() {
		return getNombre();
	}
	
	public Reporte(){
		super();
	}
	
	public Reporte(Integer id, String nombre){
		super();
		this.id = id;
		this.nombre = nombre;
	}

}
