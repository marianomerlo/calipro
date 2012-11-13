package ar.edu.utn.frba.proyecto.domain;

public class ReporteContainer extends AuditObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1106973454632135155L;

	private Integer idProducto;
	
	private String nombreProducto;
	
	private Integer idLote;
	
	private Integer cantidadDeProcesamientos;
	
	private Integer tiempoPromedio;
	
	private Integer idUsuario;

	private String nombreUsuario;
	
	private String nombreAnalisis;
	
	private Integer cantidadAnalisis;
	
	private String estado;
	
	@Override
	public String getIdentifingName() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public Integer getIdLote() {
		return idLote;
	}

	public void setIdLote(Integer idLote) {
		this.idLote = idLote;
	}

	public Integer getCantidadDeProcesamientos() {
		return cantidadDeProcesamientos;
	}

	public void setCantidadDeProcesamientos(Integer cantidadDeProcesamientos) {
		this.cantidadDeProcesamientos = cantidadDeProcesamientos;
	}

	public Integer getTiempoPromedio() {
		return tiempoPromedio;
	}

	public void setTiempoPromedio(Integer tiempoPromedio) {
		this.tiempoPromedio = tiempoPromedio;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreAnalisis() {
		return nombreAnalisis;
	}

	public void setNombreAnalisis(String nombreAnalisis) {
		this.nombreAnalisis = nombreAnalisis;
	}

	public Integer getCantidadAnalisis() {
		return cantidadAnalisis;
	}

	public void setCantidadAnalisis(Integer cantidadAnalisis) {
		this.cantidadAnalisis = cantidadAnalisis;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the nombreUsuario
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	/**
	 * @param nombreUsuario the nombreUsuario to set
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	

}
