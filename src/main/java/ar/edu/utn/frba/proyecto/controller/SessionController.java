package ar.edu.utn.frba.proyecto.controller;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.tabview.TabView;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;

import ar.edu.utn.frba.proyecto.dao.Dao;
import ar.edu.utn.frba.proyecto.dao.impl.UserDao;
import ar.edu.utn.frba.proyecto.domain.Usuario;

@SuppressWarnings("rawtypes")
@ManagedBean
@SessionScoped
public class SessionController extends BaseController {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7522603026332167457L;

	@ManagedProperty("#{userDao}")
	private UserDao userDao;
	
	@ManagedProperty("#{profileController}")
	private ProfileController profileController;
	
	private String legajo;
	private String password;
	
	private Usuario loggedUser = null;
	
	private int activeIndexTab;
	
	public void login(){
		
		Usuario tempUser = getUserDao().getByUnique(new Usuario(null, null, null, null, legajo, null, null));
		if ( tempUser != null && password.equals(tempUser.getContraseña()) ){
			tempUser.setPerfiles(getProfileController().getProfilesByUser(tempUser));
			loggedUser = tempUser;
		} else {
			String errorMessage = "Legajo y/o contraseña invalidos";
			FacesContext.getCurrentInstance().addMessage("loginGrowlMessages",
					new FacesMessage(FacesMessage.SEVERITY_WARN, errorMessage,null));
		}
		RequestContext.getCurrentInstance().addCallbackParam("loggedIn", loggedUser != null ? true : false);
	}
	
	public void logout(){
		loggedUser = null;
	}
	
	public final void onTabChange(final TabChangeEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> params = context.getExternalContext()
				.getRequestParameterMap();
		TabView tabView = (TabView) event.getComponent();
		String activeIndexValue = params.get(tabView.getClientId(context)
				+ "_tabindex");
		this.setActiveIndexTab(Integer.parseInt(activeIndexValue));
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getActiveIndexTab() {
		return activeIndexTab;
	}

	public void setActiveIndexTab(int activeIndexTab) {
		this.activeIndexTab = activeIndexTab;
	}

	@Override
	protected Dao getDao() {
		// TODO Auto-generated method stub
		return null;
	}

	public ProfileController getProfileController() {
		return profileController;
	}

	public void setProfileController(ProfileController profileController) {
		this.profileController = profileController;
	}
	
	public Usuario getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(Usuario loggedUser) {
		this.loggedUser = loggedUser;
	}
}
