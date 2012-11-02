package ar.edu.utn.frba.proyecto.domain;

import ar.edu.utn.frba.proyecto.constants.ConstantsDatatable;

public class Maquinaria extends AuditObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2798619299693591041L;

	private Estado estado;
	
	public Maquinaria(){
		
	}
	
	public Maquinaria ( Maquinaria maquina){
		this.id = maquina.getId();
		this.nombre = maquina.getNombre();
		this.estado = new Estado(maquina.getEstado().getId(),maquina.getEstado().getNombre());
	}
	
	public Maquinaria (Integer id, String nombre, Integer estado){
		this.id = id;
		this.nombre = nombre;
		this.estado = new Estado(estado,null);
	}
	
	public Maquinaria (Integer id){
		super();
		this.id = id;
	}
	
	@Override
	public String getIdentifingName() {
		return getNombre();
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
		return ConstantsDatatable.ESTADO_GROUPID_MAQUINARIA;
	}
	
	
	
}
