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
	
	private List<Solicitud> itemsInProcess;

	private List<Solicitud> itemsHist;

	@Override
	protected SolicitudDao getDao() {
		return solicitudDao;
	}
	
	public List<Solicitud> getItemsInProcess() {
		if (this.itemsInProcess == null || this.itemsInProcess.size() == 0) {
			refreshItemsInProcess();
		}
		return this.itemsInProcess;
	}
	
	public void setItemsInProcess(List<Solicitud> itemsInProcess) {
		this.itemsInProcess = itemsInProcess;
	}
	
	public void refreshItemsInProcess(){
		List<Solicitud> tempList = getDao().getSolicitudesInProcess();
		refreshCurrentCriterios(tempList);
		setItemsInProcess(tempList);
	}

	public List<Solicitud> getItemsHist(){
		if ( this.itemsHist == null || this.itemsHist.size() == 0){
			refreshItemsHist();
		}
		return this.itemsHist;
	}
	
	public void refreshItemsHist(){
		List<Solicitud> tempList = getDao().getSolicitudesHist();
		refreshCurrentCriterios(tempList);
		setItemsHist(tempList);
	}
	
	public void nextState(Solicitud solicitud){
		
		getDao().nextState(solicitud);
		
		refreshItemsInProcess();
	}
	
	public void refreshCurrentCriterios(List<Solicitud> tempList) {
		for (Solicitud solicitud : tempList) {
			for (Criterio criterio : solicitud.getAnalisis().getCriterios()) {
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
	}
	
	private boolean readyToFinish;
	
	public void endSolicitud(){
		for ( Criterio criterio : getSelectedItem().getAnalisis().getCriterios()){
			if ( criterio.getValorObtenido() == null || "".equals(criterio.getValorObtenido())){
				setReadyToFinish(false);
				return;
			}
		}
		setReadyToFinish(true);
		
	}

	public void finishSolicitud(){
		getDao().finishSolicitud(getSelectedItem());
		
		refreshItemsInProcess();
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

	/**
	 * @return the readyToFinish
	 */
	public boolean isReadyToFinish() {
		return readyToFinish;
	}

	/**
	 * @param readyToFinish the readyToFinish to set
	 */
	public void setReadyToFinish(boolean readyToFinish) {
		this.readyToFinish = readyToFinish;
	}

}
