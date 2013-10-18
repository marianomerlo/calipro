package ar.edu.utn.frba.proyecto.domain;

public class Sector extends BaseObject {
	
	private Estadio estadio;
	
	public Sector(){
		super();
	}
	
	public Sector(Sector sector, Integer idEstadio){
		super();
		this.id = sector.getId();
		this.nombre = sector.getNombre();
		this.estadio = new Estadio(idEstadio);
	}
	
	public Sector(Integer idSector, String nombre, Integer idEstadio){
		super();
		this.id = idSector;
		this.nombre = nombre;
		this.estadio = new Estadio(idEstadio);;
	}

	@Override
	public String getIdentifingName() {
		return this.nombre;
	}
	
	@Override
	public int hashCode() {
		return ( nombre.length() * 31 ) ^ id;
	}	
	
	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof Sector) {
			Sector other = (Sector) arg0;
		      return (nombre.equals(other.nombre) && id == other.id);
		    }
		    return false;
	}

	/**
	 * @return the estadio
	 */
	public Estadio getEstadio() {
		return estadio;
	}

	/**
	 * @param estadio the estadio to set
	 */
	public void setEstadio(Estadio estadio) {
		this.estadio = estadio;
	}

	
}
