package ar.edu.utn.frba.proyecto.domain;

import java.io.Serializable;

import ar.edu.utn.frba.proyecto.constants.ConstantsDatatable;

public class Producto extends AuditObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 612447183267864545L;

	private String descripcion;

	public Producto(Integer prodId, String nombre, String descripcion){
		super();
		this.id = prodId;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public Producto(Producto producto){
		super();
		this.id = producto.getId();
		this.nombre = producto.getNombre();
		this.descripcion = producto.getDescripcion();
	}

	public Producto(Integer id){
		super();
		this.id = id;
	}
	
	public Producto(){
		super();
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String getIdentifingName() {
		return getNombre();
	}

	@Override
	public Integer getStateGroupId() {
		return ConstantsDatatable.ESTADO_GROUPID_PRODUCTO;
	}
}
