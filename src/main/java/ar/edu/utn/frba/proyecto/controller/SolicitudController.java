package ar.edu.utn.frba.proyecto.controller;

import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.primefaces.model.SelectableDataModel;

import ar.edu.utn.frba.proyecto.dao.impl.SolicitudDao;
import ar.edu.utn.frba.proyecto.domain.Criterio;
import ar.edu.utn.frba.proyecto.domain.Solicitud;
import ar.edu.utn.frba.proyecto.domain.enumType.CriterioType;

public class SolicitudController extends BaseAbmController<Solicitud> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7613017636493044065L;
	
	@ManagedProperty("#{solicitudDao}")
	private SolicitudDao solicitudDao;
	
	@ManagedProperty("#{criterioController}")
	private CriterioController criterioController;

	public void setSolicitudDao(SolicitudDao solicitudDao) {
		this.solicitudDao = solicitudDao;
	}
	
	private String[] obtainedValues;
	
	private List<Criterio> refreshedCriterios;
	
	private List<Solicitud> itemsHist;

	@Override
	protected SolicitudDao getDao() {
		return solicitudDao;
	}
	
	@Override
	public List<Solicitud> getItems(){
		if ( this.items == null || this.items.size() == 0){
			refreshItems();
		}
		return this.items;
	}
	
	@Override
	public void refreshItems(){
		this.items = getDao().getSolicitudesInProcess();
	}

	public List<Solicitud> getItemsHist(){
		if ( this.itemsHist == null || this.itemsHist.size() == 0){
			refreshItemsHist();
		}
		return this.itemsHist;
	}
	
	public void refreshItemsHist(){
		this.itemsHist = getDao().getSolicitudesHist();
	}
	
	public void nextState(Solicitud solicitud){
		
		getDao().nextState(solicitud);
		
		refreshItems();
	}
	
	public void refreshCurrentCriterios() {
		setRefreshedCriterios(getCriterioController().getCriteriosByAnalisis(
				getSelectedItem().getAnalisis()));

		for (Criterio criterio : getRefreshedCriterios()) {
			List<String> values = getCriterioController()
					.getValuesFromCriterio(criterio);
			if (values.size() > 0) {
				criterio.setTipo(CriterioType.COMBO);
				criterio.setOpciones(values);
			} else {
				criterio.setTipo(CriterioType.TEXTO);
			}
		}
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
		return null;
	}

	/**
	 * @param itemsHist the itemsHist to set
	 */
	public void setItemsHist(List<Solicitud> itemsHist) {
		this.itemsHist = itemsHist;
	}

	/**
	 * @return the refreshedCriterios
	 */
	public List<Criterio> getRefreshedCriterios() {
		return refreshedCriterios;
	}

	/**
	 * @param refreshedCriterios the refreshedCriterios to set
	 */
	public void setRefreshedCriterios(List<Criterio> refreshedCriterios) {
		this.refreshedCriterios = refreshedCriterios;
	}

	/**
	 * @return the obtainedValues
	 */
	public String[] getObtainedValues() {
		if (this.obtainedValues == null || this.obtainedValues.length == 0) {
			this.obtainedValues = new String[50];
		}

		return obtainedValues;
	}

	/**
	 * @param obtainedValues the obtainedValues to set
	 */
	public void setObtainedValues(String[] obtainedValues) {
		this.obtainedValues = obtainedValues;
	}

	/**
	 * @return the criterioController
	 */
	public CriterioController getCriterioController() {
		return criterioController;
	}

	/**
	 * @param criterioController the criterioController to set
	 */
	public void setCriterioController(CriterioController criterioController) {
		this.criterioController = criterioController;
	}

}
