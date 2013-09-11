package ar.edu.utn.frba.proyecto.domain;

import ar.edu.utn.frba.proyecto.constants.ConstantsDatatable;

public class Festival extends AuditObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8725336945008072031L;

	private String fechaInicio;
	
	private Integer cantidadDias;
	
	private Estado estado;
	
	
	public Festival(){
		super();
	}
	
	public Festival(Festival festival){
		super();
		this.id = festival.getId();
		this.nombre = festival.getNombre();
		this.fechaInicio = festival.getFechaInicio();
		this.cantidadDias = festival.getCantidadDias();
		this.estado = festival.getEstado();
	}
	
	public Festival(Integer id,
					String nombre,
					String fechaInicio,
					Integer cantidadDias,
					Integer estado){
		
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.cantidadDias = cantidadDias;
		this.estado = new Estado(estado, null);
		
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Integer getCantidadDias() {
		return cantidadDias;
	}

	public void setCantidadDias(Integer cantidadDias) {
		this.cantidadDias = cantidadDias;
	}

	@Override
	public String getIdentifingName() {
		return this.nombre;
	}

	/**
	 * @return the estado
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	@Override
	public Integer getStateGroupId() {
		return ConstantsDatatable.ESTADO_GROUPID_FESTIVAL;
	}

}
