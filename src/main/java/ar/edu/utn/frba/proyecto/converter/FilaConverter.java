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
import ar.edu.utn.frba.proyecto.domain.Fila;

@ManagedBean
@SessionScoped
public class FilaConverter implements Converter {

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
	  
	                for (Fila fila : getItems()) {  
	                    if (fila.getId() == id) {  
	                        return fila;  
	                    }  
	                }  
	  
	            } catch(NumberFormatException exception) {  
	                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid sector"));  
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
            return String.valueOf(((Fila) value).getId());  
        }  
	}

	/**
	 * @return the analisisController
	 */
	public EstadioController getEstadioController() {
		return estadioController;
	}

	/**
	 * @param estadioController the analisisController to set
	 */
	public void setEstadioController(EstadioController estadioController) {
		this.estadioController = estadioController;
	}

	/**
	 * @return the analisis
	 */
	public List<Fila> getItems() {
		return getEstadioController().getFilasDisponiblesFromSector(getEstadioController().getSectorElegido());
	}

}
