package ar.edu.utn.frba.proyecto.datamodel;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import ar.edu.utn.frba.proyecto.domain.Producto;

public class ProductDataModel extends ListDataModel<Producto> implements
		SelectableDataModel<Producto>, Serializable {

	public ProductDataModel(List<Producto> data){
		super(data);
	}
	
	@Override
	public Producto getRowData(String rowKey) {

		for ( Producto producto : (List<Producto>) getWrappedData()){
			if ( rowKey.equals(producto.getProdId()))
					return producto;
		}
		
		return null;
	}

	@Override
	public Object getRowKey(Producto producto) {
		return producto.getProdId();
	}

}
