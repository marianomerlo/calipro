package ar.edu.utn.frba.proyecto.controller;

import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.primefaces.model.SelectableDataModel;

import ar.edu.utn.frba.proyecto.dao.impl.SolicitudDao;
import ar.edu.utn.frba.proyecto.domain.Solicitud;

public class SolicitudController extends BaseAbmController<Solicitud> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7613017636493044065L;
	
	@ManagedProperty("#{solicitudDao}")
	private SolicitudDao solicitudDao;

	public void setSolicitudDao(SolicitudDao solicitudDao) {
		this.solicitudDao = solicitudDao;
	}

	@Override
	protected SolicitudDao getDao() {
		return solicitudDao;
	}

	@Override
	protected Solicitud newBaseItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Solicitud newBaseItem(Solicitud item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean isDifferent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected SelectableDataModel<Solicitud> newDataModel(List<Solicitud> all) {
		// TODO Auto-generated method stub
		return null;
	}

}
