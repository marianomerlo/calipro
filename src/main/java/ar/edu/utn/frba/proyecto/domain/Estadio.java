package ar.edu.utn.frba.proyecto.domain;

public class Estadio extends BaseObject {

	public Estadio(Integer id){
		super();
		this.id = id;
	}
	
	@Override
	public String getIdentifingName() {
		return this.nombre;
	}

}
