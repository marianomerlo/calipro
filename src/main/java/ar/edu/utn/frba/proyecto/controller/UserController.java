package ar.edu.utn.frba.proyecto.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.SelectableDataModel;

import ar.edu.utn.frba.proyecto.dao.AbmDao;
import ar.edu.utn.frba.proyecto.dao.impl.UserDao;
import ar.edu.utn.frba.proyecto.datamodel.UserDataModel;
import ar.edu.utn.frba.proyecto.domain.Estado;
import ar.edu.utn.frba.proyecto.domain.Profile;
import ar.edu.utn.frba.proyecto.domain.Usuario;

@ManagedBean
@ViewScoped
public class UserController extends BaseAbmController<Usuario> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8390243907161766746L;
	
	@ManagedProperty("#{userDao}")
	private UserDao userDao;

	@ManagedProperty("#{estadoController}")
	private EstadoController estadoController;

	@ManagedProperty("#{profileController}")
	private ProfileController profileController;

	
	private Profile[] selectedProfiles = new Profile[4];

	private List<Estado> availableStates;
	
	private Integer selectedItemEstadoId = 0;
	
	@Override
	public void addItem(){
		if ( getSelectedProfiles().length > 0){
			super.addItem();
			
		} else {
			String confirmMessage = "Debes seleccionar al menos un perfil";
			FacesContext.getCurrentInstance().addMessage(getAddMessageKey(),
					new FacesMessage(FacesMessage.SEVERITY_FATAL, confirmMessage, null));
		}
	}
	
	@Override
	protected void extraAddItemProcess() {
		getProfileController().addProfilesToUser(getCurrentItem(), getSelectedProfiles());
		super.extraAddItemProcess();
	}
	
	@Override
	public void updateItem(){
		getSelectedItem().setEstado(new Estado(getSelectedItemEstadoId(),null));
		super.updateItem();
	}
	
	@Override
	protected void extraUpdateItemProcess() {
		
		if (hasProfilesChanged()) {
			if (getSelectedProfiles().length > 0) {
				getProfileController().removeProfilesFromUser(getSelectedItem());
				getProfileController().addProfilesToUser(getSelectedItem(),getSelectedProfiles());
			} else {
				String confirmMessage = "Debes seleccionar al menos un perfil";
				FacesContext.getCurrentInstance().addMessage(getUpdateMessageKey(),
						new FacesMessage(FacesMessage.SEVERITY_FATAL,
								confirmMessage, null));
				return;
			}
		}
		super.extraUpdateItemProcess();
	}
	
	@Override
	protected void extraGetItemProcess(Usuario usuario) {
		usuario.setPerfiles(getProfileController().getProfilesByUser(usuario));
		usuario.setEstado(getEstadoController().get(usuario.getEstado()));
	}
	
	@Override
	protected void extraGetItemsProcess(){
		for (Usuario usuario : getItems()) {
			extraGetItemProcess(usuario);
		}
	}
	
	@Override
	protected void extraResetCurrentProcess() {
		setSelectedProfiles(new Profile[4]);
	}
	
	@Override
	protected void extraRestoreOriginalItemProcess() {
		extraResetCurrentProcess();
	}
	
	@Override
	protected AbmDao<Usuario> getDao() {
		return this.userDao;
	}

	@Override
	protected Usuario newBaseItem() {
		return new Usuario();
	}

	@Override
	protected Usuario newBaseItem(Usuario item) {
		return new Usuario(item);
	}

	@Override
	protected boolean isDifferent() {
		return !getOriginalSelectedItem().getAlias().equals(getSelectedItem().getAlias()) ||
				!getOriginalSelectedItem().getNombre().equals(getSelectedItem().getNombre()) || 
				!getOriginalSelectedItem().getApellido().equals(getSelectedItem().getApellido()) ||
				!getOriginalSelectedItem().getLegajo().equals(getSelectedItem().getLegajo()) ||
				!getOriginalSelectedItem().getContraseña().equals(getSelectedItem().getContraseña()) ||
				hasStateChanged() ||
				hasProfilesChanged();
				
	}

	private boolean hasStateChanged() {
		return !(getOriginalSelectedItem().getEstado().getId() == getSelectedItem().getEstado().getId());
	}

	private boolean hasProfilesChanged() {
		return !(getOriginalSelectedItem().getPerfiles().containsAll(getSelectedProfilesAsList()) &&
				getOriginalSelectedItem().getPerfiles().size() == getSelectedProfiles().length);
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	protected SelectableDataModel<Usuario> newDataModel(List<Usuario> all) {
		return new UserDataModel(all);
	}

	/**
	 * @return the selectedProfiles
	 */
	public Profile[] getSelectedProfiles() {
		return selectedProfiles;
	}

	/**
	 * @param selectedProfiles the selectedProfiles to set
	 */
	public void setSelectedProfiles(Profile[] selectedProfiles) {
		this.selectedProfiles = selectedProfiles;
	}

	public Profile[] getUserProfilesAsArray(){
		return selectedItem.getPerfiles().toArray(new Profile[4]);
	}
	
	public List<Profile> getSelectedProfilesAsList(){
		if ( getSelectedProfiles() != null && getSelectedProfiles().length > 0)
			return Arrays.asList(getSelectedProfiles());
		
		return new ArrayList<Profile>();
	}

	public EstadoController getEstadoController() {
		return estadoController;
	}

	public void setEstadoController(EstadoController estadoController) {
		this.estadoController = estadoController;
	}

	/**
	 * @return the profileController
	 */
	public ProfileController getProfileController() {
		return profileController;
	}

	/**
	 * @param profileController the profileController to set
	 */
	public void setProfileController(ProfileController profileController) {
		this.profileController = profileController;
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
}
