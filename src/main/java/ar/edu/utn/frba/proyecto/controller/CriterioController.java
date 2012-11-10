package ar.edu.utn.frba.proyecto.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.primefaces.model.SelectableDataModel;

import ar.edu.utn.frba.proyecto.dao.impl.CriterioDao;
import ar.edu.utn.frba.proyecto.datamodel.CriterioDataModel;
import ar.edu.utn.frba.proyecto.datamodel.StringDataModel;
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

	private String selectedType;

	private List<String> currentCriterioValues;

	private String valueToBeAdded;

	private SelectableDataModel<String> stringDataModel;
	
	private String[] selectedAddedValues;

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

	public SelectableDataModel<String> newStringDataModel(Collection<String> all) {
		return new StringDataModel(all) {
		};
	}

	public void addValue() {
		getCurrentCriterioValues().add(getValueToBeAdded());
	}
	
	@Override
	public void addItem(){
		getCurrentItem().setOpciones(getCurrentCriterioValues());
		super.addItem();
	}

	public void deleteValues() {
		for (String value : getSelectedAddedValues())
			getCurrentCriterioValues().remove(value);
		
		resetSelectedAddedValues();
	}

	private void resetSelectedAddedValues() {
		this.selectedAddedValues = null;
		
	}

	/**
	 * @return the selectedType
	 */
	public String getSelectedType() {
		return selectedType;
	}

	/**
	 * @param selectedType
	 *            the selectedType to set
	 */
	public void setSelectedType(String selectedType) {
		this.selectedType = selectedType;
	}

	/**
	 * @return the currentCriterioValues
	 */
	public List<String> getCurrentCriterioValues() {
		if (this.currentCriterioValues == null) {
			this.currentCriterioValues = new ArrayList<String>();
		}
		return currentCriterioValues;
	}

	/**
	 * @param currentCriterioValues
	 *            the currentCriterioValues to set
	 */
	public void setCurrentCriterioValues(List<String> currentCriterioValues) {
		this.currentCriterioValues = currentCriterioValues;
	}

	/**
	 * @return the valueToBeAdded
	 */
	public String getValueToBeAdded() {
		return valueToBeAdded;
	}

	/**
	 * @param valueToBeAdded
	 *            the valueToBeAdded to set
	 */
	public void setValueToBeAdded(String valueToBeAdded) {
		this.valueToBeAdded = valueToBeAdded;
	}

	/**
	 * @return the stringDataModel
	 */
	public SelectableDataModel<String> getStringDataModel() {
		this.stringDataModel = newStringDataModel(getCurrentCriterioValues());

		return this.stringDataModel;
	}

	/**
	 * @param stringDataModel
	 *            the stringDataModel to set
	 */
	public void setStringDataModel(SelectableDataModel<String> stringDataModel) {
		this.stringDataModel = stringDataModel;
	}

	/**
	 * @return the selectedAddedValues
	 */
	public String[] getSelectedAddedValues() {
		if (this.selectedAddedValues == null)
			this.selectedAddedValues = new String[50];
			
		return selectedAddedValues;
	}

	/**
	 * @param selectedAddedValues the selectedAddedValues to set
	 */
	public void setSelectedAddedValues(String[] selectedAddedValues) {
		this.selectedAddedValues = selectedAddedValues;
	}

}
