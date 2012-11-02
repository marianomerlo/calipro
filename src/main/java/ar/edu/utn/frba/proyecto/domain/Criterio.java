package ar.edu.utn.frba.proyecto.domain;

import ar.edu.utn.frba.proyecto.domain.enumType.CriterioType;

public class Criterio extends AuditObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4799782913312761309L;
	
	private CriterioType tipo;
	
	private String valorEsperado;

	public Criterio(Integer id, String nombre, CriterioType tipo, String valorEsperado){
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.valorEsperado = valorEsperado;
	}

	public Criterio(Integer id, String nombre){
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Criterio(Criterio criterio){
		super();
		this.id = criterio.getId();
		this.nombre = criterio.getNombre();
		this.tipo = criterio.getTipo();
		this.valorEsperado = criterio.getValorEsperado();
	}
	
	
	public Criterio() {
		super();
	}


	@Override
	public String getIdentifingName() {
		return getNombre();
	}

	/**
	 * @return the tipo
	 */
	public CriterioType getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(CriterioType tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the valorEsperado
	 */
	public String getValorEsperado() {
		return valorEsperado;
	}

	/**
	 * @param valorEsperado the valorEsperado to set
	 */
	public void setValorEsperado(String valorEsperado) {
		this.valorEsperado = valorEsperado;
	}

}
