package ar.edu.utn.frba.proyecto.domain;


public class Vista {

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
