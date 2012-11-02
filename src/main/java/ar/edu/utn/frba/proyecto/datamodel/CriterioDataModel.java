package ar.edu.utn.frba.proyecto.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import ar.edu.utn.frba.proyecto.domain.Criterio;

public class CriterioDataModel extends ListDataModel<Criterio> implements
		SelectableDataModel<Criterio> {

	public CriterioDataModel(){
		
	}
	
	public CriterioDataModel(List<Criterio> data){
		super(data);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Criterio getRowData(String arg0) {
		List<Criterio> criteriosList = (List<Criterio>) getWrappedData();  
        
        for(Criterio criterio : criteriosList) { 
        	String criterioId = String.valueOf(criterio.getId());
            if(criterioId.equals(arg0))  
                return criterio;  
        }  
          
        return null;  
	}

	@Override
	public Object getRowKey(Criterio arg0) {
		return arg0.getId();
	}

}
