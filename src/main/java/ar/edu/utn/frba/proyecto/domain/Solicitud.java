package ar.edu.utn.frba.proyecto.domain;

import java.io.Serializable;

public class Solicitud extends AuditObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4446891915202991364L;
	
	private Lote lote;
	
	private String fechaRecibido;
	
	private Estado estado;

	@Override
	public String getIdentifingName() {
		// TODO Auto-generated method stub
		return null;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public String getFechaRecibido() {
		return fechaRecibido;
	}

	public void setFechaRecibido(String fechaRecibido) {
		this.fechaRecibido = fechaRecibido;
	}

	/**
	 * @return the estado
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
