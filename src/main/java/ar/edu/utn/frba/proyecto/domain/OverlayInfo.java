package ar.edu.utn.frba.proyecto.domain;

public class OverlayInfo {
	
	private String fechaUltimaMod;
	
	private String valorObtenido;

	
	public OverlayInfo(){
		super();
	}
	
	public OverlayInfo(String fecha, String valor){
		super();
		this.fechaUltimaMod = fecha;
		this.valorObtenido = valor;
	}
	
	public String getFechaUltimaMod() {
		return fechaUltimaMod;
	}

	public void setFechaUltimaMod(String fechaUltimaMod) {
		this.fechaUltimaMod = fechaUltimaMod;
	}

	public String getValorObtenido() {
		return valorObtenido;
	}

	public void setValorObtenido(String valorObtenido) {
		this.valorObtenido = valorObtenido;
	}
}
