package ar.edu.utn.frba.proyecto.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import ar.edu.utn.frba.proyecto.domain.Profile;
import ar.edu.utn.frba.proyecto.domain.User;
import ar.edu.utn.frba.proyecto.domain.Vista;
import ar.edu.utn.frba.proyecto.service.RestService;


@ManagedBean
@SessionScoped
public class UserController extends BaseController {

	private static final long serialVersionUID = 4452567671269942318L;
	
//	@ManagedProperty("#{userService}")
//	private UserService userService;
	
	@ManagedProperty("#{ticketRestService}")
	private RestService ticketRestService;
	
	private String username;
	private String password;
	private User user;
	
	private List<Profile> profiles;
	
	private String redirectPath = "https://www.box.net/api/1.0/auth/";
	
	public String login( ActionEvent event) {
		RequestContext context = RequestContext.getCurrentInstance();  
		try {
//			this.user = this.userService.login(this.username, this.password);
//			String ticket = askTicket();
	        context.addCallbackParam("status", "SUCCESS");  
	        context.addCallbackParam("url", redirectPath );  
		} catch (Exception e) {
			this.addMessage("Please verify your user/password", "", FacesMessage.SEVERITY_ERROR);
			context.addCallbackParam("status", "LOGIN_FAILED");
		} finally {
			this.password = null;
		}
		String outcome = "error.xhtml";
		return outcome;
	}
	
	public void logout() {
		this.user = null;
		this.username = null;
	}
	
	public boolean isSignedIn() {
		return this.user != null;
	}
	
//	public void setUserService(UserService userService) {
//		this.userService = userService;
//	}
	
	public void setTicketRestService(RestService ticketRestService) {
		this.ticketRestService = ticketRestService;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User getUser() {
		return user;
	}
	
	private String askTicket() {
		return this.ticketRestService.getData();
	}

	/**
	 * @return the profiles
	 */
	public List<Profile> getProfiles() {
		if ( profiles == null || profiles.size() == 0){
			profiles = new ArrayList<Profile>();
			profiles.add( new Profile("Desarrollo", "3", "desa", new Vista("Productos", "produccionView.xhtml"), new Vista("Analisis", "analisisView.xhtml")));
			profiles.add( new Profile("Produccion", "1", "prod"));
			profiles.add( new Profile("Calidad", "2", "cali"));
			profiles.add( new Profile("Supervision", "4", "super"));
//			profiles.add( new Profile("Calidad", "2", "cali", new Vista("En Proceso", "procesoView.xhtml"), new Vista("Historico","historicView.xhtml")));
//			profiles.add( new Profile("Desarrollo", "3", "desa", new Vista("Productos","productosView.xhtml"), new Vista("Analisis", "analisisView.xhtml")));
//			profiles.add( new Profile("Supervision", "4", "super", new Vista("Usuarios", "usuariosView.xhtml"), new Vista("Reportes", "reportesView.xhtml")));
		}
		return profiles;
	}

	/**
	 * @param profiles the profiles to set
	 */
	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}
	
	
}
