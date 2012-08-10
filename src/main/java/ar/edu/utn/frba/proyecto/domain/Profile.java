package ar.edu.utn.frba.proyecto.domain;


import java.util.Arrays;
import java.util.List;

public class Profile {
	
	public Profile(){
		super();
	}
	
	public Profile ( String name, String number, String position, Vista... vistas){
		super();
		this.name = name;
		this.number = number;
		this.position = position;
		this.vistas = vistas != null ? Arrays.asList(vistas) : null;
	}

	private String name;
	
	private String number;
	
	private String position;
	
	private List<Vista> vistas;

	
	
	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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
	
}
