package ar.edu.utn.frba.proyecto.controller;

import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.primefaces.model.SelectableDataModel;

import ar.edu.utn.frba.proyecto.dao.impl.CriterioDao;
import ar.edu.utn.frba.proyecto.datamodel.CriterioDataModel;
import ar.edu.utn.frba.proyecto.domain.Analisis;
import ar.edu.utn.frba.proyecto.domain.Criterio;

public class CriterioController extends BaseAbmController<Criterio> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3925962140570413559L;

	@ManagedProperty("#{criterioDao}")
	private CriterioDao criterioDao;

	@Override
	protected CriterioDao getDao() {
		return this.criterioDao;
	}

	/**
	 * @param criterioDao
	 *            the criterioDao to set
	 */
	public void setCriterioDao(CriterioDao criterioDao) {
		this.criterioDao = criterioDao;
	}

	public void addCriteriosToAnalisis(Analisis analisis,
			Criterio[] selectedCriterios) {
		getDao().addCriteriosToAnalisis(analisis, selectedCriterios);
	}

	public void removeCriteriosFromAnalisis(Analisis selectedItem) {
		getDao().removeCriteriosFromAnalisis(selectedItem);
	}

	public List<Criterio> getCriteriosByAnalisis(Analisis analisis) {
		return getDao().getCriteriosByAnalisis(analisis);
	}

	@Override
	protected Criterio newBaseItem() {
		return new Criterio();
	}

	@Override
	protected Criterio newBaseItem(Criterio item) {
		return new Criterio(item);
	}

	@Override
	protected boolean isDifferent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected SelectableDataModel<Criterio> newDataModel(List<Criterio> all) {
		return new CriterioDataModel(all);
	}

}
