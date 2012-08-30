package ar.edu.utn.frba.proyecto.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.model.SelectableDataModel;

import ar.edu.utn.frba.proyecto.dao.Dao;
import ar.edu.utn.frba.proyecto.dao.UserDao;
import ar.edu.utn.frba.proyecto.domain.Profile;
import ar.edu.utn.frba.proyecto.domain.Usuario;
import ar.edu.utn.frba.proyecto.domain.Vista;

@ManagedBean
@ViewScoped
public class UserController extends BaseController<Usuario> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8390243907161766746L;
	
	@ManagedProperty("#{userDao}")
	private UserDao userDao;
	
	private boolean logged = false;;
	
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
			logged = true;
			RequestContext.getCurrentInstance().addCallbackParam("url", "index.xhtml");
		} else {
			String errorMessage = "Legajo y/o contraseña invalidos";
			FacesContext.getCurrentInstance().addMessage("loginGrowlMessages",
					new FacesMessage(FacesMessage.SEVERITY_WARN, errorMessage,null));
		}
		RequestContext.getCurrentInstance().addCallbackParam("loggedIn", logged);
	}
	
	public void logout(){
		logged = false;
	}

	/**
	 * @return the logged
	 */
	public boolean isLogged() {
		return logged;
	}

	/**
	 * @param logged the logged to set
	 */
	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	@Override
	protected SelectableDataModel<Usuario> newDataModel(List<Usuario> all) {
		// TODO Auto-generated method stub
		return null;
	}

}
