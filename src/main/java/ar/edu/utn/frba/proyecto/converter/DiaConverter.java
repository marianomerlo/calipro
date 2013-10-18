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

import ar.edu.utn.frba.proyecto.controller.EstadioController;
import ar.edu.utn.frba.proyecto.domain.Dia;

@ManagedBean
@SessionScoped
public class DiaConverter implements Converter {

	@ManagedProperty("#{estadioController}")
	private EstadioController estadioController;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String submittedValue) {
		 if (submittedValue.trim().equals("")) {  
	            return null;  
	        } else {  
	            try {  
	                int id = Integer.parseInt(submittedValue);  
	  
	                for (Dia dia : getItems()) {  
	                    if (dia.getId() == id) {  
	                        return dia;  
	                    }  
	                }  
	  
	            } catch(NumberFormatException exception) {  
	                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid banda"));  
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
            return String.valueOf(((Dia) value).getId());  
        }  
	}

	/**
	 * @return the analisisController
	 */
	public EstadioController getEstadioController() {
		return estadioController;
	}

	/**
	 * @param bandaController the analisisController to set
	 */
	public void setEstadioController(EstadioController estadioController) {
		this.estadioController = estadioController;
	}

	/**
	 * @return the analisis
	 */
	public List<Dia> getItems() {
		return getEstadioController().getFestivalElegido().getDias();
	}

}
