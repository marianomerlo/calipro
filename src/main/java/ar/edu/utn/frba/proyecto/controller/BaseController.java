package ar.edu.utn.frba.proyecto.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class BaseController implements Serializable {

	private static final long serialVersionUID = 3567648080222548124L;
	
	protected String INITIAL_NAME = "initialName";
	protected String INITIAL_DESC = "initialDesc";
	
	protected void addMessage(String summary, String detail) {
		this.addMessage(summary, detail, FacesMessage.SEVERITY_INFO);
	}
	
	protected void addMessage(String summary, String detail, FacesMessage.Severity severity) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}
	
}
