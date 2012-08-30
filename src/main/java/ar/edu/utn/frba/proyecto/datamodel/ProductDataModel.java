package ar.edu.utn.frba.proyecto.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import ar.edu.utn.frba.proyecto.domain.Producto;

public class ProductDataModel extends ListDataModel<Producto> implements
		SelectableDataModel<Producto> {

	public ProductDataModel(){
		
	}
	
	public ProductDataModel(List<Producto> productos){
		super(productos);
	}
	
	@Override
	public Integer getRowKey(Producto arg0) {
		return arg0.getId();
	}

	@Override
	public Producto getRowData(String arg0) {
        List<Producto> productos = (List<Producto>) getWrappedData();  
        
        for(Producto producto : productos) { 
        	String productId = String.valueOf(producto.getId());
            if(productId.equals(arg0))  
                return producto;  
        }  
          
        return null;  
	}

}
