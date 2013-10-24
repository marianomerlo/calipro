package ar.edu.utn.frba.proyecto.domain;

import java.util.List;

public class Factura extends AuditObject {
	
	private String fecha;
	
	private Double costoTotal;
	
	private List<Entrada> entradas;
	
	public Factura(){
		super();
	}
	
	public Factura(Factura factura){
		super();
		this.id = factura.getId();
		this.fecha = factura.getFecha();
		this.entradas = factura.getEntradas();
	}
	
	public Factura(Integer id, String fecha, Double costoTotal){
		super();
		this.id = id;
		this.fecha = fecha;
		this.costoTotal = costoTotal;
	}

	public Factura(Integer id, String fecha, List<Entrada> entradas){
		super();
		this.id = id;
		this.fecha = fecha;
		this.entradas = entradas;
		this.costoTotal = getCalculatedCostoTotal();
	}

	public Double getCalculatedCostoTotal() {
		Double result = new Double(0);
		
		if (entradas != null) {
			for (Entrada entrada : entradas) {
				result = result
						+ (entrada.getPrecioBase() + entrada
								.getPrecioExtraPorBandas())
						* (100 - entrada.getCategoria().getDescuento()) / 100;
			}
		}
		return result;
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

	/**
	 * @return the entradas
	 */
	public List<Entrada> getEntradas() {
		return entradas;
	}

	/**
	 * @param entradas the entradas to set
	 */
	public void setEntradas(List<Entrada> entradas) {
		this.entradas = entradas;
	}

}
