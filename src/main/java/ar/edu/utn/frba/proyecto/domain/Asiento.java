package ar.edu.utn.frba.proyecto.domain;

public class Asiento extends BaseObject {
	
	private Fila fila;
	
	public Asiento(){
		super();
	}
	
	public Asiento(Integer id, Fila fila){
		super();
		this.id = id;
		this.nombre = "Asiento " + String.valueOf(id);
		this.fila = fila;
	}
	
	public Asiento(Asiento asiento){
		super();
		this.id = asiento.getId();
		this.nombre = asiento.getNombre();
		this.fila = asiento.getFila();
	}
	
	@Override
	public String getIdentifingName() {
		return this.getNombre();
	}

	/**
	 * @return the fila
	 */
	public Fila getFila() {
		return fila;
	}

	/**
	 * @param fila the fila to set
	 */
	public void setFila(Fila fila) {
		this.fila = fila;
	}
	
	@Override
	public int hashCode() {
		return ( nombre.length() * 31 ) ^ id;
	}	
	
	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof Asiento) {
			Asiento other = (Asiento) arg0;
		      return (nombre.equals(other.nombre) && id == other.id);
		    }
		    return false;
	}

}
