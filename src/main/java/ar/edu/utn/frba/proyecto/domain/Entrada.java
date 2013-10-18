package ar.edu.utn.frba.proyecto.domain;

public class Entrada extends BaseObject {
	
	private Festival festival;
	
	private Dia dia;
	
	private Sector sector;
	
	private Fila fila;
	
	private Asiento asiento;
	
	private Double precioBase;
	
	private Double precioExtraPorBandas;
	
	public Entrada(){
		super();
	}
	
	public Entrada(Dia dia, Sector sector, Fila fila, Asiento asiento){
		super();
		this.dia = dia;
		this.sector = sector;
		this.fila = fila;
		this.asiento = asiento;
	}
	
	@Override
	public String getIdentifingName() {
		return this.getNombre();
	}

	@Override
	public int hashCode() {
		return ( nombre.length() * 31 ) ^ id;
	}	
	
	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof Entrada) {
			Entrada other = (Entrada) arg0;
		      return (nombre.equals(other.nombre) && id == other.id);
		    }
		    return false;
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public Fila getFila() {
		return fila;
	}

	public void setFila(Fila fila) {
		this.fila = fila;
	}

	public Asiento getAsiento() {
		return asiento;
	}

	public void setAsiento(Asiento asiento) {
		this.asiento = asiento;
	}

	/**
	 * @return the precioBase
	 */
	public Double getPrecioBase() {
		return precioBase;
	}

	/**
	 * @param precioBase the precioBase to set
	 */
	public void setPrecioBase(Double precioBase) {
		this.precioBase = precioBase;
	}

	/**
	 * @return the precioExtraPorBandas
	 */
	public Double getPrecioExtraPorBandas() {
		return precioExtraPorBandas;
	}

	/**
	 * @param precioExtraPorBandas the precioExtraPorBandas to set
	 */
	public void setPrecioExtraPorBandas(Double precioExtraPorBandas) {
		this.precioExtraPorBandas = precioExtraPorBandas;
	}

	/**
	 * @return the dia
	 */
	public Dia getDia() {
		return dia;
	}

	/**
	 * @param dia the dia to set
	 */
	public void setDia(Dia dia) {
		this.dia = dia;
	}

	/**
	 * @return the festival
	 */
	public Festival getFestival() {
		return festival;
	}

	/**
	 * @param festival the festival to set
	 */
	public void setFestival(Festival festival) {
		this.festival = festival;
	}

}
