package ar.edu.utn.frba.proyecto.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.util.StringUtils;

import ar.edu.utn.frba.proyecto.datamodel.AnalisisDataModel;
import ar.edu.utn.frba.proyecto.domain.Analisis;

@ManagedBean
@SessionScoped
public class AnalisisController extends AbstractController {

	private static final long serialVersionUID = 4452567671269942318L;

	private List<Analisis> analisis;
	private String fakeId = String.valueOf(Math.random());

	private Analisis[] selectedAnalisis;

	private Analisis currentAnalisis = new Analisis(fakeId, "");
	private Analisis selectedSingleAnalisis = new Analisis(fakeId, INITIAL_NAME);
	private Analisis tempSelectedAnalisis = new Analisis(fakeId, INITIAL_NAME);

	private AnalisisDataModel analisisDataModel;
	private AnalisisDataModel histAnalisisDataModel;

	public List<Analisis> getAnalisis() {
		if (this.analisis == null)
			this.analisis = new ArrayList<Analisis>();

		return this.analisis;
	}

	public List<Analisis> getAnalisisHist() {
		this.analisis = new ArrayList<Analisis>();
		this.analisis.add(new Analisis("A", "phA"));
		this.analisis.add(new Analisis("B", "phB"));
		this.analisis.add(new Analisis("C", "phC"));
		this.analisis.add(new Analisis("D", "phD"));
		this.analisis.add(new Analisis("E", "phE"));
		this.analisis.add(new Analisis("F", "phF"));
		this.analisis.add(new Analisis("G", "phG"));
		return this.analisis;
	}

	public void resetCurrent() {
		this.currentAnalisis = new Analisis(currentAnalisis.getAnalisisId()
				+ "AA", "");
	}

	public void addAnalisis() {
		getAnalisis().add(currentAnalisis);
		setAnalisisDataModel(new AnalisisDataModel(getAnalisis()));

		String confirmMessage = "Analisis " + currentAnalisis.getNombre()
				+ " creado satisfactoriamente";
		FacesContext.getCurrentInstance().addMessage(
				"addAnalisisGrowlMessageKeys",
				new FacesMessage(FacesMessage.SEVERITY_INFO, confirmMessage,
						null));
		resetCurrent();
	}
	
	public void updateAnalisis() {

		for (Analisis analisis : getAnalisis()) {
			if (analisis.getAnalisisId().equals(selectedSingleAnalisis.getAnalisisId())) {
				analisis.setNombre(selectedSingleAnalisis.getNombre());
			}
		}
		setAnalisisDataModel(new AnalisisDataModel(getAnalisis()));
		String confirmMessage = "Analisis " + selectedSingleAnalisis.getNombre() + " modificado satisfactoriamente";
        FacesContext.getCurrentInstance().addMessage("updateAnalisisGrowlMessageKeys", new FacesMessage(FacesMessage.SEVERITY_INFO,confirmMessage, null));

	}
	
	public void deleteAnalisis(){
		getAnalisis().removeAll(Arrays.asList(getSelectedAnalisis()));
		setAnalisisDataModel(new AnalisisDataModel(getAnalisis()));
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
			List<String> analisisIds = new ArrayList<String>();
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
	 * @return the histAnalisisDataModel
	 */
	public AnalisisDataModel getHistAnalisisDataModel() {
		if (this.histAnalisisDataModel == null) {
			this.histAnalisisDataModel = new AnalisisDataModel(
					getAnalisisHist());
		}

		return this.histAnalisisDataModel;
	}

	/**
	 * @param histAnalisisDataModel
	 *            the histAnalisisDataModel to set
	 */
	public void setHistAnalisisDataModel(AnalisisDataModel histAnalisisDataModel) {
		this.histAnalisisDataModel = histAnalisisDataModel;
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
}
