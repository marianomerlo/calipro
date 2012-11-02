package ar.edu.utn.frba.proyecto.domain;


public class Receta extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6671483450590891015L;
	
	private Integer version;
	
	private Integer producto;

	public Receta(){
		super();
	}
	
	public Receta(Integer version, Integer producto){
		super();
		this.version = version;
		this.producto = producto;
	}
	
	/**
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * @return the producto
	 */
	public Integer getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(Integer producto) {
		this.producto = producto;
	}

	@Override
	public String getIdentifingName() {
		return null;
	}

}
