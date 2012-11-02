package ar.edu.utn.frba.proyecto.domain.enumType;

public enum CriterioType {
	
	TEXTO("texto"),
	COMBO("combo");

	private String value;
	
	private CriterioType(String value){
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
	
}
