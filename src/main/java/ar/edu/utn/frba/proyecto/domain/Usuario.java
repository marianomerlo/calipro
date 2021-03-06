package ar.edu.utn.frba.proyecto.domain;

import java.io.Serializable;
import java.util.List;

import ar.edu.utn.frba.proyecto.constants.ConstantsDatatable;

public class Usuario extends AuditObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5247586026878417405L;
	
	private String alias;
	
	private String apellido;
	
	private String legajo;
	
	private Estado estado;
	
	private String contraseña;
	
	private List<Profile> perfiles;

	public Usuario(Integer id,String alias, String nombre, String apellido, String legajo, Integer estado, String contraseña){
		super();
		this.id = id;
		this.alias = alias;
		this.nombre = nombre;
		this.apellido = apellido;
		this.legajo = legajo;
		this.estado = new Estado(estado, null);
		this.contraseña = contraseña;
	}
	
	public Usuario(Usuario usuario){
		super();
		this.id = usuario.getId();
		this.alias = usuario.getAlias();
		this.nombre = usuario.getNombre();
		this.apellido = usuario.getApellido();
		this.contraseña = usuario.getContraseña();
		this.legajo = usuario.getLegajo();
		this.estado = new Estado(usuario.getEstado().getId(),usuario.getEstado().getNombre());
		this.perfiles = usuario.getPerfiles();
	}
	
	public Usuario( Integer id){
		super();
		this.id = id;
	}
	
	public Usuario() {
		super();
	}

	@Override
	public String getIdentifingName() {
		return getAlias();
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the perfiles
	 */
	public List<Profile> getPerfiles() {
		return perfiles;
	}

	/**
	 * @param perfiles the perfiles to set
	 */
	public void setPerfiles(List<Profile> perfiles) {
		this.perfiles = perfiles;
	}

	@Override
	public Integer getStateGroupId() {
		return ConstantsDatatable.ESTADO_GROUPID_USUARIO;
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

}
