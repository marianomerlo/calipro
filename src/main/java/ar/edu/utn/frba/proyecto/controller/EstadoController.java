package ar.edu.utn.frba.proyecto.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import ar.edu.utn.frba.proyecto.dao.impl.EstadoDao;
import ar.edu.utn.frba.proyecto.domain.BaseObject;
import ar.edu.utn.frba.proyecto.domain.Estado;

@ManagedBean
@RequestScoped
public class EstadoController extends BaseController<Estado> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4714868011118024943L;

	@ManagedProperty("#{estadoDao}")
	private EstadoDao estadoDao;
	
	@Override
	protected EstadoDao getDao() {
		return this.estadoDao;
	}

	/**
	 * @param estadoDao the estadoDao to set
	 */
	public void setEstadoDao(EstadoDao estadoDao) {
		this.estadoDao = estadoDao;
	}
	
	public List<Estado> getEstadosFromElement(BaseObject element){
		return getDao().getEstadosFromElement(element);
	}

}
