package ar.edu.utn.frba.proyecto.controller;

import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.tabview.TabView;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.SelectableDataModel;

import ar.edu.utn.frba.proyecto.constants.ConstantsDatatable;
import ar.edu.utn.frba.proyecto.dao.Dao;
import ar.edu.utn.frba.proyecto.domain.Profile;
import ar.edu.utn.frba.proyecto.domain.Usuario;

@SuppressWarnings("rawtypes")
@ManagedBean
@SessionScoped
public class SessionController extends BaseController {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7522603026332167457L;

	@ManagedProperty("#{userController}")
	private UserController userController;
	
	@ManagedProperty("#{profileController}")
	private ProfileController profileController;
	
	private String alias;
	private String password;
	
	private Usuario loggedUser = null;
	
	private int activeInnerIndexTab;
	private int activeProfileIndexTab;

	private Profile activeProfile;
	
	public void login(){
		
		Usuario tempUser = getUserController().getByUnique(new Usuario(null, alias, null, null, null, null, null));
		if ( tempUser != null && password.equals(tempUser.getContraseña()) &&
				!(ConstantsDatatable.ESTADO_USUARIO_DESHABILITADO == tempUser.getEstado().getId())){
			tempUser.setPerfiles(getProfileController().getProfilesByUser(tempUser));
			setActiveProfile(tempUser.getPerfiles().get(0));
			setActiveProfileIndexTab(0);
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
		setActiveInnerIndexTab(Integer.parseInt(activeIndexValue));
	}

	public final void onProfileChange(final Profile perfil, final int activeProfileIndexTab ) {
		setActiveProfile(perfil);
		setActiveProfileIndexTab(activeProfileIndexTab);
		setActiveInnerIndexTab(0);
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getActiveInnerIndexTab() {
		return activeInnerIndexTab;
	}

	public void setActiveInnerIndexTab(int activeInnerIndexTab) {
		this.activeInnerIndexTab = activeInnerIndexTab;
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

	/**
	 * @return the activeProfile
	 */
	public Profile getActiveProfile() {
		return activeProfile;
	}

	/**
	 * @param activeProfile the activeProfile to set
	 */
	public void setActiveProfile(Profile activeProfile) {
		this.activeProfile = activeProfile;
	}

	/**
	 * @return the activeProfileIndexTab
	 */
	public int getActiveProfileIndexTab() {
		return activeProfileIndexTab;
	}

	/**
	 * @param activeProfileIndexTab the activeProfileIndexTab to set
	 */
	public void setActiveProfileIndexTab(int activeProfileIndexTab) {
		this.activeProfileIndexTab = activeProfileIndexTab;
	}

	/**
	 * @return the userController
	 */
	public UserController getUserController() {
		return userController;
	}

	/**
	 * @param userController the userController to set
	 */
	public void setUserController(UserController userController) {
		this.userController = userController;
	}
	
	public void updateUser(){
		getUserController().setSelectedItem(getLoggedUser());
		getUserController().updateItem();
	}

	@Override
	protected SelectableDataModel newDataModel(List all) {
		// TODO Auto-generated method stub
		return null;
	}
}
