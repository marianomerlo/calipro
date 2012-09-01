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

	
	private Profile[] selectedProfiles = new Profile[4];
	
	@Override
	protected void extraAddItemProcess() {
		if (selectedProfiles.length > 0) {
			getGenericDao().addProfilesToUser(currentItem, selectedProfiles);
			super.extraAddItemProcess();
		} else {
			String confirmMessage = "Debes seleccionar al menos un perfil";
			FacesContext.getCurrentInstance().addMessage(getAddMessageKey(),
					new FacesMessage(FacesMessage.SEVERITY_FATAL, confirmMessage,
							null));
		}
	}
	
	@Override
	public void extraUpdateItemProcess() {
		if (hasProfilesChanged()) {
			if (selectedProfiles.length > 0) {
				getGenericDao().removeProfilesFromUser(selectedItem);
				getGenericDao().addProfilesToUser(selectedItem,selectedProfiles);
				super.extraUpdateItemProcess();
			} else {
				String confirmMessage = "Debes seleccionar al menos un perfil";
				FacesContext.getCurrentInstance().addMessage(getUpdateMessageKey(),
						new FacesMessage(FacesMessage.SEVERITY_FATAL,
								confirmMessage, null));
			}
		}
	}
	
	@Override
	public void extraRefreshItemsProcess() {
		for (Usuario usuario : getItems()) {
			usuario.setPerfiles(getGenericDao().getProfilesByUser(usuario));
		}
	}
	
	@Override
	public void extraGetItemsProcess(){
		for (Usuario usuario : getItems()) {
			usuario.setPerfiles(getGenericDao().getProfilesByUser(usuario));
		}
	}
	
	@Override
	protected void extraResetCurrentProcess() {
		setSelectedProfiles(new Profile[4]);
	}
	
	@Override
	protected AbmDao<Usuario> getDao() {
		return this.userDao;
	}

	@Override
	protected Usuario newBaseItem() {
		return new Usuario(0, "", "", "", "", "");
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
				hasProfilesChanged();
				
	}

	private boolean hasProfilesChanged() {
		return !(getOriginalSelectedItem().getPerfiles().containsAll(getSelectedProfilesAsList()) &&
				getOriginalSelectedItem().getPerfiles().size() == selectedProfiles.length);
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
		if ( selectedProfiles != null && selectedProfiles.length > 0)
			return Arrays.asList(selectedProfiles);
		
		return new ArrayList<Profile>();
	}
}
