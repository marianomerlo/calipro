package ar.edu.utn.frba.proyecto.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import ar.edu.utn.frba.proyecto.domain.User;
import ar.edu.utn.frba.proyecto.service.RestService;


/**
 * 
 * @author mariano.gonzalez@mulesoft.com
 *
 */
@ManagedBean
@SessionScoped
public class UserController extends AbstractController {

	private static final long serialVersionUID = 4452567671269942318L;
	
//	@ManagedProperty("#{userService}")
//	private UserService userService;
	
	@ManagedProperty("#{ticketRestService}")
	private RestService ticketRestService;
	
	private String username;
	private String password;
	private User user;
	
	private String redirectPath = "https://www.box.net/api/1.0/auth/";
	
	public void login( ActionEvent event) {
		RequestContext context = RequestContext.getCurrentInstance();  
		try {
//			this.user = this.userService.login(this.username, this.password);
			String ticket = askTicket();
	        context.addCallbackParam("status", "SUCCESS");  
	        context.addCallbackParam("url", redirectPath + ticket );  
		} catch (Exception e) {
			this.addMessage("Please verify your user/password", "", FacesMessage.SEVERITY_ERROR);
			context.addCallbackParam("status", "LOGIN_FAILED");
		} finally {
			this.password = null;
		}
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
	
	
}
