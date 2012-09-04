package ar.edu.utn.frba.proyecto.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import ar.edu.utn.frba.proyecto.domain.Analisis;

public class AnalisisDataModel extends ListDataModel<Analisis> implements
		SelectableDataModel<Analisis> {

	public AnalisisDataModel(){
		
	}
	
	public AnalisisDataModel(List<Analisis> data){
		super(data);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Analisis getRowData(String arg0) {
		List<Analisis> analisisList = (List<Analisis>) getWrappedData();  
        
        for(Analisis analisis : analisisList) { 
        	String analisisId = String.valueOf(analisis.getId());
            if(analisisId.equals(arg0))  
                return analisis;  
        }  
          
        return null;  
	}

	@Override
	public Object getRowKey(Analisis arg0) {
		return arg0.getId();
	}

}
