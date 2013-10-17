package ar.edu.utn.frba.proyecto.domain;

public class Factura extends AuditObject {
	
	private String fecha;
	
	private Double costoTotal;
	
	public Factura(){
		super();
	}
	
	public Factura(Factura factura){
		super();
		this.id = factura.getId();
		this.fecha = factura.getFecha();
		this.costoTotal = factura.getCostoTotal();
	}
	
	public Factura(Integer id, String fecha, Double costoTotal){
		super();
		this.id = id;
		this.fecha = fecha;
		this.costoTotal = costoTotal;
	}

	@Override
	public String getIdentifingName() {
		return this.nombre;
	}
	
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Double getCostoTotal() {
		return costoTotal;
	}

	public void setCostoTotal(Double costoTotal) {
		this.costoTotal = costoTotal;
	}

}
