package ar.edu.utn.frba.proyecto.domain;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.proyecto.constants.ConstantsDatatable;

public class Festival extends AuditObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8725336945008072031L;

	private String fechaInicio;
	
	private Integer cantidadDias;
	
	private Estado estado;
	
	private List<Dia> dias;
	
	public Festival(){
		super();
	}
	
	public Festival(Festival festival){
		super();
		this.id = festival.getId();
		this.nombre = festival.getNombre();
		this.fechaInicio = festival.getFechaInicio();
		this.cantidadDias = festival.getCantidadDias();
		this.estado = festival.getEstado();
	}
	
	public Festival(Integer id,
					String nombre,
					String fechaInicio,
					Integer cantidadDias,
					Integer estado){
		
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.cantidadDias = cantidadDias;
		this.estado = new Estado(estado, null);
		
	}
	
	public Dia getDia(Integer idDia){
		for (Dia dia : dias){
			if (idDia == dia.getId())
				return dia;
		}
		
		return null;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Integer getCantidadDias() {
		return cantidadDias;
	}

	public void setCantidadDias(Integer cantidadDias) {
		this.cantidadDias = cantidadDias;
	}

	@Override
	public String getIdentifingName() {
		return this.nombre;
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
	
	@Override
	public Integer getStateGroupId() {
		return ConstantsDatatable.ESTADO_GROUPID_FESTIVAL;
	}

	/**
	 * @return the dias
	 */
	public List<Dia> getDias() {
		return dias;
	}

	/**
	 * @param dias the dias to set
	 */
	public void setDias(List<Dia> dias) {
		this.dias = dias;
	}
	
	public List<Banda> getBandasFromFestival(){
		List<Banda> result = new ArrayList<Banda>();
		
		for (Dia dia : getDias() ){
			result.addAll(dia.getBandas());
		}
		
		return result;
	}
	
	@Override
	public int hashCode() {
		return ( nombre.length() * 31 ) ^ id;
	}	
	
	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof Festival) {
			Festival other = (Festival) arg0;
		      return (nombre.equals(other.nombre) && id == other.id);
		    }
		    return false;
	}

}
