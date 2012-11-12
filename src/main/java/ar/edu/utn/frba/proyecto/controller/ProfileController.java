package ar.edu.utn.frba.proyecto.controller;

import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.primefaces.model.SelectableDataModel;

import ar.edu.utn.frba.proyecto.dao.impl.ProfileDao;
import ar.edu.utn.frba.proyecto.domain.Profile;
import ar.edu.utn.frba.proyecto.domain.Usuario;

public class ProfileController extends BaseController<Profile> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8625535476945880957L;
	
	@ManagedProperty("#{profileDao}")
	private ProfileDao profileDao;
	
	@Override
	protected ProfileDao getDao() {
		return this.profileDao;
	}

	public void setProfileDao(ProfileDao profileDao) {
		this.profileDao = profileDao;
	}
	
	public void addProfilesToUser(Usuario user, Profile[] selectedProfiles){
		getDao().addProfilesToUser(user, selectedProfiles);
	}
	
	public void removeProfilesFromUser(Usuario user){
		getDao().removeProfilesFromUser(user);
	}
	
	public List<Profile> getProfilesByUser(Usuario user){
		return getDao().getProfilesByUser(user);
	}

	@Override
	protected SelectableDataModel<Profile> newDataModel(List<Profile> all) {
		// TODO Auto-generated method stub
		return null;
	}
}
