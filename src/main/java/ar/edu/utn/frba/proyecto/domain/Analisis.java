package ar.edu.utn.frba.proyecto.domain;

public class Analisis extends AuditObject {

	private String analisisId;
	
	private String name;

	public Analisis ( String analisisId, String name){
		super();
		this.analisisId = analisisId;
		this.name = name;
	}
	
	public String getAnalisisId() {
		return analisisId;
	}

	public void setAnalisisId(String analisisId) {
		this.analisisId = analisisId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
