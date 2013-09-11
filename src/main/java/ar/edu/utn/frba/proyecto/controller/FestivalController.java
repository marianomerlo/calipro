package ar.edu.utn.frba.proyecto.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.SelectableDataModel;

import ar.edu.utn.frba.proyecto.dao.AbmDao;
import ar.edu.utn.frba.proyecto.dao.impl.FestDao;
import ar.edu.utn.frba.proyecto.datamodel.FestivalDataModel;
import ar.edu.utn.frba.proyecto.domain.Estado;
import ar.edu.utn.frba.proyecto.domain.Festival;

@ManagedBean
@SessionScoped
public class FestivalController extends BaseAbmController<Festival> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8390243907161766746L;
	
	@ManagedProperty("#{festDao}")
	private FestDao festDao;

	@ManagedProperty("#{estadoController}")
	private EstadoController estadoController;

	private List<Estado> availableStates;
	
	private Integer selectedItemEstadoId = 0;
	
//	@Override
//	public void addItem(){
//		if ( getSelectedProfiles().length > 0){
//			super.addItem();
//			
//		} else {
//			String confirmMessage = "Debes seleccionar al menos un perfil";
//			FacesContext.getCurrentInstance().addMessage(getAddMessageKey(),
//					new FacesMessage(FacesMessage.SEVERITY_FATAL, confirmMessage, null));
//		}
//	}
	
//	@Override
//	protected void extraAddItemProcess() {
//		getProfileController().addProfilesToUser(getCurrentItem(), getSelectedProfiles());
//		super.extraAddItemProcess();
//	}
	
//	@Override
//	protected void extraUpdateItemProcess() {
//		
//		if (hasProfilesChanged()) {
//			if (getSelectedProfiles().length > 0) {
//				getProfileController().removeProfilesFromUser(getSelectedItem());
//				getProfileController().addProfilesToUser(getSelectedItem(),getSelectedProfiles());
//			} else {
//				String confirmMessage = "Debes seleccionar al menos un perfil";
//				FacesContext.getCurrentInstance().addMessage(getUpdateMessageKey(),
//						new FacesMessage(FacesMessage.SEVERITY_FATAL,
//								confirmMessage, null));
//				return;
//			}
//		}
//		super.extraUpdateItemProcess();
//	}
	
	@Override
	protected void extraGetItemProcess(Festival festival) {
		festival.setEstado(getEstadoController().get(festival.getEstado()));
	}
	
	@Override
	protected void extraRestoreOriginalItemProcess() {
		extraResetCurrentProcess();
	}
	
	@Override
	protected AbmDao<Festival> getDao() {
		return this.festDao;
	}

	@Override
	protected Festival newBaseItem() {
		return new Festival();
	}

	@Override
	protected Festival newBaseItem(Festival item) {
		return new Festival(item);
	}

	@Override
	protected boolean isDifferent() {
		return !getOriginalSelectedItem().getNombre().equals(getSelectedItem().getNombre()) ||
				!getOriginalSelectedItem().getFechaInicio().equals(getSelectedItem().getFechaInicio()) || 
				!getOriginalSelectedItem().getCantidadDias().equals(getSelectedItem().getCantidadDias()) ||
				!getOriginalSelectedItem().getEstado().equals(getSelectedItem().getEstado()) ||
				hasStateChanged();
				
	}

	private boolean hasStateChanged() {
		return !(getOriginalSelectedItem().getEstado().getId() == getSelectedItem().getEstado().getId());
	}

	public void setFestDao(FestDao festDao) {
		this.festDao = festDao;
	}

	@Override
	protected SelectableDataModel<Festival> newDataModel(List<Festival> all) {
		return new FestivalDataModel(all);
	}

	public EstadoController getEstadoController() {
		return estadoController;
	}

	public void setEstadoController(EstadoController estadoController) {
		this.estadoController = estadoController;
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
	 * @return the selectedItemEstado
	 */
	public Integer getSelectedItemEstadoId() {
		if ( this.selectedItemEstadoId == null){
			this.selectedItemEstadoId = getSelectedItem().getEstado().getId();
		}
		return selectedItemEstadoId;
	}

	/**
	 * @param selectedItemEstado the selectedItemEstado to set
	 */
	public void setSelectedItemEstadoId(Integer selectedItemEstadoId) {
		this.selectedItemEstadoId = selectedItemEstadoId;
	}
	
	public Festival getByUnique(Festival festival){
		return getDao().getByUnique(festival);
	}
}
