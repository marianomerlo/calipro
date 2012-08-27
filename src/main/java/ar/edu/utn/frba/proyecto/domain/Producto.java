package ar.edu.utn.frba.proyecto.domain;

import java.io.Serializable;

public class Producto extends AuditObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 612447183267864545L;

	private Integer prodId;
	
	private String nombre;
	
	private String descripcion;

	public Producto(Integer prodId, String nombre, String descripcion){
		super();
		this.prodId = prodId;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public Producto(Producto producto){
		super();
		this.prodId = producto.getProdId();
		this.nombre = producto.getNombre();
		this.descripcion = producto.getDescripcion();
	}
	
	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
