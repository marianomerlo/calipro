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

import ar.edu.utn.frba.proyecto.controller.FestivalController;
import ar.edu.utn.frba.proyecto.domain.Festival;

@ManagedBean
@SessionScoped
public class FestivalConverter implements Converter {

	@ManagedProperty("#{festivalController}")
	private FestivalController festivalController;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String submittedValue) {
		 if (submittedValue.trim().equals("")) {  
	            return null;  
	        } else {  
	            try {  
	                int id = Integer.parseInt(submittedValue);  
	  
	                for (Festival festival : getItems()) {  
	                    if (festival.getId() == id) {  
	                        return festival;  
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
            return String.valueOf(((Festival) value).getId());  
        }  
	}

	/**
	 * @return the analisisController
	 */
	public FestivalController getFestivalController() {
		return festivalController;
	}

	/**
	 * @param bandaController the analisisController to set
	 */
	public void setFestivalController(FestivalController festivalController) {
		this.festivalController = festivalController;
	}

	/**
	 * @return the analisis
	 */
	public List<Festival> getItems() {
		return getFestivalController().getItems();
	}

}
