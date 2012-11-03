package ar.edu.utn.frba.proyecto.domain;

import java.io.Serializable;


public class Vista implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1561169901047092429L;

	private String name;
	
	private String view;
	
	public Vista(){
		super();
	}
	
	public Vista ( String name, String view ){
		super();
		this.name = name;
		this.view = view;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}
}
