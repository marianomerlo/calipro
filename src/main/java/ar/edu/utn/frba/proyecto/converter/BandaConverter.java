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

import ar.edu.utn.frba.proyecto.controller.BandaController;
import ar.edu.utn.frba.proyecto.domain.Banda;

@ManagedBean
@SessionScoped
public class BandaConverter implements Converter {

	@ManagedProperty("#{bandaController}")
	private BandaController bandaController;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String submittedValue) {
		 if (submittedValue.trim().equals("")) {  
	            return null;  
	        } else {  
	            try {  
	                int id = Integer.parseInt(submittedValue);  
	  
	                for (Banda banda : getItems()) {  
	                    if (banda.getId() == id) {  
	                        return banda;  
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
            return String.valueOf(((Banda) value).getId());  
        }  
	}

	/**
	 * @return the analisisController
	 */
	public BandaController getBandaController() {
		return bandaController;
	}

	/**
	 * @param bandaController the analisisController to set
	 */
	public void setBandaController(BandaController bandaController) {
		this.bandaController = bandaController;
	}

	/**
	 * @return the analisis
	 */
	public List<Banda> getItems() {
		return getBandaController().getItems();
	}

}
