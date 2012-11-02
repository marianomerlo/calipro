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

import ar.edu.utn.frba.proyecto.controller.ProductController;
import ar.edu.utn.frba.proyecto.domain.Producto;

@ManagedBean
@SessionScoped
public class ProductConverter implements Converter {

	@ManagedProperty("#{productController}")
	private ProductController productController;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String submittedValue) {
		 if (submittedValue.trim().equals("")) {  
	            return null;  
	        } else {  
	            try {  
	                int id = Integer.parseInt(submittedValue);  
	  
	                for (Producto producto : getItems()) {  
	                    if (producto.getId() == id) {  
	                        return producto;  
	                    }  
	                }  
	  
	            } catch(NumberFormatException exception) {  
	                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid player"));  
	            }  
	        }  
	  
	        return null;  
	}

	private List<Producto> getItems() {
		return getProductController().getItems();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value == null || value.equals("")) {  
            return "";  
        } else {  
            return String.valueOf(((Producto) value).getId());  
        }  
	}

	/**
	 * @return the productController
	 */
	public ProductController getProductController() {
		return productController;
	}

	/**
	 * @param productController the productController to set
	 */
	public void setProductController(ProductController productController) {
		this.productController = productController;
	}
}
