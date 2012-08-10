package ar.edu.utn.frba.proyecto.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import ar.edu.utn.frba.proyecto.domain.Analisis;

public class AnalisisDataModel extends ListDataModel<Analisis> implements
		SelectableDataModel<Analisis> {

	public AnalisisDataModel(List<Analisis> data){
		super(data);
	}
	
	@Override
	public Analisis getRowData(String rowKey) {

		for ( Analisis analisis : (List<Analisis>) getWrappedData()){
			if ( rowKey.equals(analisis.getAnalisisId()))
					return analisis;
		}
		
		return null;
	}

	@Override
	public Object getRowKey(Analisis analisis) {
		return analisis.getAnalisisId();
	}

}
