package ar.edu.utn.frba.proyecto.domain;

public class Lote extends AuditObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1484136531275753360L;

	
	private Producto producto;
	
	private Maquinaria maquinaria;
	
	private Estado estado;
	
	public Lote(Lote lote){
		super();
		this.id = lote.getId();
		this.setProducto(new Producto(lote.getProducto()));
		this.maquinaria = lote.getMaquinaria();
		this.estado = new Estado(lote.getEstado().getId(),lote.getEstado().getNombre());
	}
	
	public Lote(Integer id, Producto producto, Maquinaria maquinaria, Integer idEstado){
		super();
		this.id = id;
		this.producto = producto;
		this.maquinaria = maquinaria;
		this.estado = new Estado(idEstado,null);
	}
	
	public Lote(){
		super();
	}
	
	@Override
	public String getIdentifingName() {
		return String.valueOf(getId());
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	/**
	 * @return the maquinaria
	 */
	public Maquinaria getMaquinaria() {
		return maquinaria;
	}

	/**
	 * @param maquinaria the maquinaria to set
	 */
	public void setMaquinaria(Maquinaria maquinaria) {
		this.maquinaria = maquinaria;
	}

	/**
	 * @return the producto
	 */
	public Producto getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}
