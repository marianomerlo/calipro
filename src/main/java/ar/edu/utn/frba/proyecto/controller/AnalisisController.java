package ar.edu.utn.frba.proyecto.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.util.StringUtils;

import ar.edu.utn.frba.proyecto.dao.AnalisisDao;
import ar.edu.utn.frba.proyecto.datamodel.AnalisisDataModel;
import ar.edu.utn.frba.proyecto.domain.Analisis;

@ManagedBean
@SessionScoped
public class AnalisisController extends BaseController {

	private static final long serialVersionUID = 4452567671269942318L;

	private List<Analisis> analisis;

	private Analisis[] selectedAnalisis;

	private Analisis currentAnalisis = new Analisis(0, "");
	private Analisis selectedSingleAnalisis = new Analisis(0, INITIAL_NAME);
	private Analisis tempSelectedAnalisis = new Analisis(0, INITIAL_NAME);

	private AnalisisDataModel analisisDataModel;

	@ManagedProperty("#{analisisDao}")
	private AnalisisDao analisisDao;

	public List<Analisis> getAnalisis() {
		if (this.analisis == null)
			this.analisis = analisisDao.getAll();

		return this.analisis;
	}

	public void resetCurrent() {
		this.currentAnalisis = new Analisis(0, "");
	}

	public void addAnalisis() {
		analisisDao.add(currentAnalisis);

		String confirmMessage = "Analisis " + currentAnalisis.getNombre()
				+ " creado satisfactoriamente";
		FacesContext.getCurrentInstance().addMessage(
				"addAnalisisGrowlMessageKeys",
				new FacesMessage(FacesMessage.SEVERITY_INFO, confirmMessage,
						null));
		resetCurrent();
		refreshAnalisis();
	}
	
	private void refreshAnalisis() {
		this.analisis = analisisDao.getAll();
	}

	public void updateAnalisis() {

		if (!tempSelectedAnalisis.getNombre().equals(selectedSingleAnalisis.getNombre())){
			analisisDao.update(selectedSingleAnalisis);

			String confirmMessage = "Analisis " + selectedSingleAnalisis.getNombre() + " modificado satisfactoriamente";
			FacesContext.getCurrentInstance().addMessage("updateAnalisisGrowlMessageKeys", new FacesMessage(FacesMessage.SEVERITY_INFO,confirmMessage, null));
			
			storeOriginalAnalisis(selectedSingleAnalisis);
			refreshAnalisis();
			
		} else {
			String errorMessage = "No ha realizado modificaciones";
			FacesContext.getCurrentInstance().addMessage("updateAnalisisGrowlMessageKeys",
					new FacesMessage(FacesMessage.SEVERITY_WARN, errorMessage,null));
		}
		

	}
	
	public void deleteAnalisis(){
		analisisDao.delete(Arrays.asList(getSelectedAnalisis()));
		refreshAnalisis();
	}

	public void storeOriginalAnalisis(Analisis analisis) {
		tempSelectedAnalisis = new Analisis(analisis);
	}

	public void restoreOriginalAnalisis() {
		selectedSingleAnalisis = new Analisis(tempSelectedAnalisis);
	}

	/**
	 * @return the analisisDataModel
	 */
	public AnalisisDataModel getAnalisisDataModel() {
		if (analisisDataModel == null) {
			analisisDataModel = new AnalisisDataModel(getAnalisis());
		}
		return analisisDataModel;
	}
	
	public void resetSelectedAnalisis() {
		selectedAnalisis = null;
	}
	
	public String deletedAnalisisIds(String splitter) {

		if (getSelectedAnalisis() != null && getSelectedAnalisis().length > 0) {
			List<Integer> analisisIds = new ArrayList<Integer>();
			for (Analisis analisis : getSelectedAnalisis())
				analisisIds.add(analisis.getAnalisisId());
			return StringUtils
					.collectionToDelimitedString(analisisIds, splitter);
		}

		return "";
	}

	/**
	 * @param analisisDataModel
	 *            the analisisDataModel to set
	 */
	public void setAnalisisDataModel(AnalisisDataModel analisisDataModel) {
		this.analisisDataModel = analisisDataModel;
	}

	/**
	 * @return the selectedSingleAnalisis
	 */
	public Analisis getSelectedSingleAnalisis() {
		return selectedSingleAnalisis;
	}

	/**
	 * @param selectedSingleAnalisis
	 *            the selectedSingleAnalisis to set
	 */
	public void setSelectedSingleAnalisis(Analisis selectedSingleAnalisis) {
		this.selectedSingleAnalisis = selectedSingleAnalisis;
	}

	/**
	 * @return the selectedAnalisis
	 */
	public Analisis[] getSelectedAnalisis() {
		return selectedAnalisis;
	}

	/**
	 * @param selectedAnalisis
	 *            the selectedAnalisis to set
	 */
	public void setSelectedAnalisis(Analisis[] selectedAnalisis) {
		this.selectedAnalisis = selectedAnalisis;
	}

	/**
	 * @return the currentAnalisis
	 */
	public Analisis getCurrentAnalisis() {
		return currentAnalisis;
	}

	/**
	 * @param currentAnalisis
	 *            the currentAnalisis to set
	 */
	public void setCurrentAnalisis(Analisis currentAnalisis) {
		this.currentAnalisis = currentAnalisis;
	}

	/**
	 * @return the tempSelectedAnalisis
	 */
	public Analisis getTempSelectedAnalisis() {
		return tempSelectedAnalisis;
	}

	/**
	 * @param tempSelectedAnalisis
	 *            the tempSelectedAnalisis to set
	 */
	public void setTempSelectedAnalisis(Analisis tempSelectedAnalisis) {
		this.tempSelectedAnalisis = tempSelectedAnalisis;
	}

	public AnalisisDao getAnalisisDao() {
		return analisisDao;
	}

	public void setAnalisisDao(AnalisisDao analisisDao) {
		this.analisisDao = analisisDao;
	}
}
