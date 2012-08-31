package ar.edu.utn.frba.proyecto.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

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

	
	private Profile[] selectedProfiles;
	
	@Override
	public Usuario addItem(){
		Usuario user = super.addItem();
		
		getGenericDao().addProfilesToUser(user, selectedProfiles);
		
		return user;
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
		return new Usuario(item.getId(),
							item.getAlias(),
							item.getNombre(),
							item.getApellido(),
							item.getLegajo(),
							item.getContraseña());
	}

	@Override
	protected boolean isDifferent() {
		return !getOriginalSelectedItem().getAlias().equals(getSelectedItem().getAlias()) ||
				!getOriginalSelectedItem().getNombre().equals(getSelectedItem().getNombre()) || 
				!getOriginalSelectedItem().getApellido().equals(getSelectedItem().getApellido()) ||
				!getOriginalSelectedItem().getLegajo().equals(getSelectedItem().getLegajo()) ||
				!getOriginalSelectedItem().getContraseña().equals(getSelectedItem().getContraseña());
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

}
