package ar.edu.utn.frba.proyecto.domain;

public class Producto extends AuditObject {

	private String prodId;
	
	private String nombre;
	
	private String descripcion;

	public Producto(String prodId, String nombre, String descripcion){
		super();
		this.prodId = prodId;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public String getProdId() {
		return prodId;
	}

	public void setId(String prodId) {
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
