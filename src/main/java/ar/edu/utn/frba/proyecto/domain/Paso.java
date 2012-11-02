package ar.edu.utn.frba.proyecto.domain;

import java.util.List;

public class Paso extends AuditObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2966697517978947727L;

	private String descripcion;
	
	private int productoId;
	
	private int version;
	
	private List<Analisis> analisis;
	
	public Paso(){
		super();
	}
	
	public Paso(int id, int productoId, int version, String descripcion){
		super();
		this.id = id;
		this.productoId = productoId;
		this.version = version;
		this.descripcion = descripcion;
	}
	
	public Paso(int id, int productoId, int version, String descripcion, List<Analisis> analisis){
		super();
		this.id = id;
		this.productoId = productoId;
		this.version = version;
		this.descripcion = descripcion;
		this.analisis = analisis;
	}
	
	public Paso ( Paso paso ){
		super();
		this.id = paso.getId();
		this.productoId = paso.getProductoId();
		this.version = paso.getVersion();
		this.descripcion = paso.getDescripcion();
		this.analisis = paso.getAnalisis();
	}
	
	@Override
	public String getIdentifingName() {
		return getDescripcion();
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the productoId
	 */
	public int getProductoId() {
		return productoId;
	}

	/**
	 * @param productoId the productoId to set
	 */
	public void setProductoId(int productoId) {
		this.productoId = productoId;
	}

	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * @return the analisis
	 */
	public List<Analisis> getAnalisis() {
		return analisis;
	}

	/**
	 * @param analisis the analisis to set
	 */
	public void setAnalisis(List<Analisis> analisis) {
		this.analisis = analisis;
	}

}
