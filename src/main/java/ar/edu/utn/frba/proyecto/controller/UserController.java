package ar.edu.utn.frba.proyecto.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import ar.edu.utn.frba.proyecto.dao.Dao;
import ar.edu.utn.frba.proyecto.dao.UserDao;
import ar.edu.utn.frba.proyecto.domain.Profile;
import ar.edu.utn.frba.proyecto.domain.Usuario;
import ar.edu.utn.frba.proyecto.domain.Vista;

@ManagedBean
@SessionScoped
public class UserController extends BaseController<Usuario> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8390243907161766746L;
	
	@ManagedProperty("#{userDao}")
	private UserDao userDao;
	
	private List<Profile> profiles;
	
	@Override
	protected Dao<Usuario> getDao() {
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

	public List<Profile> getProfiles() {
		if ( this.profiles == null){
			List<Profile> list = new ArrayList<Profile>();
			list.add(new Profile("Produccion", "1", "2", new Vista("Productos", "produccion.xhtml"),
					 									new Vista("Analisis", "analisis.xhtml")));
		}
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}
	
	public void login(){
		Usuario tempUser = getDao().getByUnique(getCurrentItem());
		if ( tempUser != null && getCurrentItem().getContraseña().equals(tempUser.getContraseña()) ){
			
		}
	}
	

}
