package ar.edu.utn.frba.proyecto.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import ar.edu.utn.frba.proyecto.datamodel.AnalisisDataModel;
import ar.edu.utn.frba.proyecto.domain.Analisis;
import ar.edu.utn.frba.proyecto.domain.User;
import ar.edu.utn.frba.proyecto.service.RestService;


/**
 * 
 * @author mariano.gonzalez@mulesoft.com
 *
 */
@ManagedBean
@ViewScoped
public class AnalisisController extends AbstractController {

	private static final long serialVersionUID = 4452567671269942318L;
	
//	@ManagedProperty("#{userService}")
//	private UserService userService;
	
	@ManagedProperty("#{ticketRestService}")
	private RestService ticketRestService;
	
	private String username;
	private String password;
	private User user;
	
	private List<Analisis> analisis;
	
	private Analisis selectedAnalisis;
	
	private AnalisisDataModel analisisDataModel;

	
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

	/**
	 * @return the productos
	 */
	public List<Analisis> getAnalisis() {
		this.analisis = new ArrayList<Analisis>();
		this.analisis.add(new Analisis("A", "phA"));
		this.analisis.add(new Analisis("B", "phB"));
		this.analisis.add(new Analisis("C", "phC"));
		this.analisis.add(new Analisis("D", "phD"));
		this.analisis.add(new Analisis("E", "phE"));
		this.analisis.add(new Analisis("F", "phF"));
		this.analisis.add(new Analisis("G", "phG"));
		return this.analisis;
	}

	public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Analisis Elegido", ((Analisis) event.getObject()).getName());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Analisis Unselected", ((Analisis) event.getObject()).getName());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowDblselect(SelectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) context.getApplication().getNavigationHandler();
        Flash flash = context.getExternalContext().getFlash();
        flash.put("selectedAnalisis", (Analisis) event.getObject());
        
        handler.performNavigation("carDetail");
    }

	/**
	 * @return the analisisDataModel
	 */
	public AnalisisDataModel getAnalisisDataModel() {
		if ( analisisDataModel == null){
			analisisDataModel = new AnalisisDataModel(getAnalisis());
		}
		return analisisDataModel;
	}

	/**
	 * @param analisisDataModel the analisisDataModel to set
	 */
	public void setAnalisisDataModel(AnalisisDataModel analisisDataModel) {
		this.analisisDataModel = analisisDataModel;
	}

	/**
	 * @return the selectedAnalisis
	 */
	public Analisis getSelectedAnalisis() {
		return selectedAnalisis;
	}

	/**
	 * @param selectedAnalisis the selectedAnalisis to set
	 */
	public void setSelectedAnalisis(Analisis selectedAnalisis) {
		this.selectedAnalisis = selectedAnalisis;
	}
}
