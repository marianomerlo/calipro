package ar.edu.utn.frba.proyecto.domain;


import java.util.Arrays;
import java.util.List;

public class Profile extends BaseObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2030356771612881776L;

	public Profile(){
		super();
	}
	
	public Profile ( Integer id, String name){
		super();
		this.id = id;
		this.name = name;
	}
	
	public Profile ( String name, Vista... vistas){
		super();
		this.name = name;
		this.vistas = vistas != null ? Arrays.asList(vistas) : null;
	}

	private String name;
	
	private List<Vista> vistas;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the vistas
	 */
	public List<Vista> getVistas() {
		return vistas;
	}

	/**
	 * @param vistas the vistas to set
	 */
	public void setVistas(List<Vista> vistas) {
		this.vistas = vistas;
	}

	@Override
	public String getIdentifingName() {
		return this.name;
	}
	
}
