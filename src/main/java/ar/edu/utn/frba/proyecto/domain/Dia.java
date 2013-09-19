package ar.edu.utn.frba.proyecto.domain;

import java.util.List;

public class Dia extends AuditObject {
	
	private List<Banda> bandas;
	
	private String horaInicio;
	
	public Dia(){
		super();
	}
	
	public Dia(Integer id, String horaInicio, List<Banda> bandas){
		super();
		this.id = id;
		this.nombre = "Dia " + String.valueOf(id);
		this.horaInicio = horaInicio;
		this.bandas = bandas;
	}

	public List<Banda> getBandas() {
		return bandas;
	}

	public void setBandas(List<Banda> bandas) {
		this.bandas = bandas;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	@Override
	public String getIdentifingName() {
		return this.nombre;
	}

}
