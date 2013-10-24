package ar.edu.utn.frba.proyecto.domain;

public class Categoria extends BaseObject {

	private Double descuento;
	
	public Categoria (){
		super();
	}
	
	public Categoria(Integer id, String nombre, Double descuento){
		super();
		this.id = id;
		this.nombre = nombre;
		this.descuento = descuento;
	}
	
	@Override
	public String getIdentifingName() {
		return this.nombre;
	}


	/**
	 * @return the descuento
	 */
	public Double getDescuento() {
		return descuento;
	}


	/**
	 * @param descuento the descuento to set
	 */
	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	
}
