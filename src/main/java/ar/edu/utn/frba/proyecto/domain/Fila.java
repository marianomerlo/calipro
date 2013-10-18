package ar.edu.utn.frba.proyecto.domain;

public class Fila extends BaseObject {
	
	private Sector sector;

	public Fila(){
		super();
	}
	
	public Fila(Integer id, Sector sector){
		super();
		this.id = id;
		this.sector = sector;
		this.nombre = "Fila " + String.valueOf(id);
	}
	
	public Fila(Fila fila){
		super();
		this.id = fila.getId();
		this.nombre = fila.getNombre();
		this.sector = fila.getSector();
	}
	
	
	@Override
	public String getIdentifingName() {
		return this.nombre;
	}

	/**
	 * @return the sector
	 */
	public Sector getSector() {
		return sector;
	}

	/**
	 * @param sector the sector to set
	 */
	public void setSector(Sector sector) {
		this.sector = sector;
	}
	
	@Override
	public int hashCode() {
		return ( nombre.length() * 31 ) ^ id;
	}	
	
	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof Fila) {
			Fila other = (Fila) arg0;
		      return (nombre.equals(other.nombre) && id == other.id);
		    }
		    return false;
	}

}
