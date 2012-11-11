package ar.edu.utn.frba.proyecto.domain.enumType;

public enum StatusType {
	
	SUCCESS("success"),
	ERROR("error");
	
	private String value;
	
	private StatusType(String value){
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
}
