package ar.edu.utn.frba.proyecto.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.SelectableDataModel;

import ar.edu.utn.frba.proyecto.dao.AnalisisDao;
import ar.edu.utn.frba.proyecto.dao.AbmDao;
import ar.edu.utn.frba.proyecto.datamodel.AnalisisDataModel;
import ar.edu.utn.frba.proyecto.domain.Analisis;

@ManagedBean
@ViewScoped
public class AnalisisController extends BaseController<Analisis> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3805173284502287061L;
	
	@ManagedProperty("#{analisisDao}")
	private AnalisisDao analisisDao;

	@Override
	protected AbmDao<Analisis> getDao() {
		return this.analisisDao;
	}

	@Override
	protected Analisis newBaseItem() {
		return new Analisis(0, "");
	}

	@Override
	protected Analisis newBaseItem(Analisis item) {
		return new Analisis(item.getId(), item.getNombre());
	}

	@Override
	protected boolean isDifferent() {
		return !getOriginalSelectedItem().getNombre().equals(getSelectedItem().getNombre());
	}

	public void setAnalisisDao(AnalisisDao analisisDao) {
		this.analisisDao = analisisDao;
	}

	@Override
	protected SelectableDataModel<Analisis> newDataModel(List<Analisis> all) {
		return new AnalisisDataModel(all);
	}
	
}