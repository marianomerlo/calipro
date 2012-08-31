package ar.edu.utn.frba.proyecto.controller;

import javax.faces.bean.ManagedProperty;

import ar.edu.utn.frba.proyecto.dao.Dao;
import ar.edu.utn.frba.proyecto.dao.impl.ProfileDao;
import ar.edu.utn.frba.proyecto.domain.Profile;

public class ProfileController extends BaseController<Profile> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8625535476945880957L;
	@ManagedProperty("#{profileDao}")
	private ProfileDao profileDao;
	
	@Override
	protected Dao<Profile> getDao() {
		return this.profileDao;
	}

	public void setProfileDao(ProfileDao profileDao) {
		this.profileDao = profileDao;
	}

}
