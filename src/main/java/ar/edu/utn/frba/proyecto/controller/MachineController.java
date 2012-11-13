package ar.edu.utn.frba.proyecto.controller;

import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.primefaces.model.SelectableDataModel;

import ar.edu.utn.frba.proyecto.dao.impl.MachineDao;
import ar.edu.utn.frba.proyecto.datamodel.MachineDataModel;
import ar.edu.utn.frba.proyecto.domain.Estado;
import ar.edu.utn.frba.proyecto.domain.Maquinaria;

public class MachineController extends BaseAbmController<Maquinaria> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8483057212459913932L;

	@ManagedProperty("#{machineDao}")
	private MachineDao machineDao;
	
	@ManagedProperty("#{estadoController}")
	private EstadoController estadoController;
	
	@Override
	protected MachineDao getDao() {
		return this.machineDao;
	}
	
	private List<Estado> availableStates;
	
	private Integer selectedItemEstadoId = 0;

	/**
	 * @param machineDao the machineDao to set
	 */
	public void setMachineDao(MachineDao machineDao) {
		this.machineDao = machineDao;
	}
	public List<Maquinaria> getAvailableMachines(){
		return getDao().getAvailableMachines();
	}

	@Override
	protected Maquinaria newBaseItem() {
		return new Maquinaria();
	}

	@Override
	protected Maquinaria newBaseItem(Maquinaria item) {
		return new Maquinaria(item);
	}
	
	@Override
	protected void extraGetItemProcess(Maquinaria maquinaria) {
		maquinaria.setEstado(getEstadoController().get(maquinaria.getEstado()));
	}
	
	@Override
	protected void extraGetItemsProcess(){
		for (Maquinaria maquinaria : getItems()) {
			extraGetItemProcess(maquinaria);
		}
	}
	
	@Override
	protected boolean isDifferent() {
		return !getOriginalSelectedItem().getNombre().equals(getSelectedItem().getNombre()) ||
				hasStateChanged();
	}
	
	private boolean hasStateChanged() {
		return !(getOriginalSelectedItem().getEstado().getId() == getSelectedItem().getEstado().getId());
	}

	@Override
	protected SelectableDataModel<Maquinaria> newDataModel(List<Maquinaria> all) {
		return new MachineDataModel(all);
	}

	/**
	 * @return the availableStates
	 */
	public List<Estado> getAvailableStates() {
		if ( this.availableStates == null){
			this.availableStates = getEstadoController().getEstadosFromElement(getSelectedItem());
			
		}
		return availableStates;
	}
	/**
	 * @param availableStates the availableStates to set
	 */
	public void setAvailableStates(List<Estado> availableStates) {
		this.availableStates = availableStates;
	}
	/**
	 * @return the estadoController
	 */
	public EstadoController getEstadoController() {
		return estadoController;
	}
	/**
	 * @param estadoController the estadoController to set
	 */
	public void setEstadoController(EstadoController estadoController) {
		this.estadoController = estadoController;
	}
	/**
	 * @return the selectedItemEstado
	 */
	public Integer getSelectedItemEstadoId() {
		if ( this.selectedItemEstadoId == null){
			this.selectedItemEstadoId = getSelectedItem().getEstado().getId();
		}
		return selectedItemEstadoId;
	}
	/**
	 * @param selectedItemEstadoId the selectedItemEstadoId to set
	 */
	public void setSelectedItemEstadoId(Integer selectedItemEstadoId) {
		this.selectedItemEstadoId = selectedItemEstadoId;
	}

}
