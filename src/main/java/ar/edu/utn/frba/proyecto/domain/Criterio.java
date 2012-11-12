package ar.edu.utn.frba.proyecto.domain;

import java.util.List;

import ar.edu.utn.frba.proyecto.domain.enumType.CriterioType;

public class Criterio extends AuditObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4799782913312761309L;
	
	private CriterioType tipo;
	
	private String valorEsperado;

	private String valorObtenido;
	
	private int idSolicitudAnalisis;
	
	private List<String> opciones;

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

	/**
	 * @return the opciones
	 */
	public List<String> getOpciones() {
		return opciones;
	}

	/**
	 * @param opciones the opciones to set
	 */
	public void setOpciones(List<String> opciones) {
		this.opciones = opciones;
	}
	
	@Override
	public int hashCode() {
		return ( nombre.length() * 31 ) ^ id;
	}	
	
	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof Criterio) {
			Criterio other = (Criterio) arg0;
		      return (nombre.equals(other.nombre) && id == other.id);
		    }
		    return false;
	}

	/**
	 * @return the valorObtenido
	 */
	public String getValorObtenido() {
		return valorObtenido;
	}

	/**
	 * @param valorObtenido the valorObtenido to set
	 */
	public void setValorObtenido(String valorObtenido) {
		this.valorObtenido = valorObtenido;
	}

	/**
	 * @return the idSolicitudAnalisis
	 */
	public int getIdSolicitudAnalisis() {
		return idSolicitudAnalisis;
	}

	/**
	 * @param idSolicitudAnalisis the idSolicitudAnalisis to set
	 */
	public void setIdSolicitudAnalisis(int idSolicitudAnalisis) {
		this.idSolicitudAnalisis = idSolicitudAnalisis;
	}

}
