package ar.edu.utn.frba.proyecto.domain;

import java.util.List;

public class Dia {
	
	private List<Banda> bandas;
	
	private String horaInicio;

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

}
