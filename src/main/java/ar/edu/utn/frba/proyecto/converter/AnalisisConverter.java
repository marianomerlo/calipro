package ar.edu.utn.frba.proyecto.converter;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import ar.edu.utn.frba.proyecto.controller.AnalisisController;
import ar.edu.utn.frba.proyecto.domain.Analisis;

@ManagedBean
@SessionScoped
public class AnalisisConverter implements Converter {

	@ManagedProperty("#{analisisController}")
	private AnalisisController analisisController;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String submittedValue) {
		 if (submittedValue.trim().equals("")) {  
	            return null;  
	        } else {  
	            try {  
	                int id = Integer.parseInt(submittedValue);  
	  
	                for (Analisis analisis : getItems()) {  
	                    if (analisis.getId() == id) {  
	                        return analisis;  
	                    }  
	                }  
	  
	            } catch(NumberFormatException exception) {  
	                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid player"));  
	            }  
	        }  
	  
	        return null;  
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value == null || value.equals("")) {  
            return "";  
        } else {  
            return String.valueOf(((Analisis) value).getId());  
        }  
	}

	/**
	 * @return the analisisController
	 */
	public AnalisisController getAnalisisController() {
		return analisisController;
	}

	/**
	 * @param analisisController the analisisController to set
	 */
	public void setAnalisisController(AnalisisController analisisController) {
		this.analisisController = analisisController;
	}

	/**
	 * @return the analisis
	 */
	public List<Analisis> getItems() {
		return getAnalisisController().getItems();
	}

}
