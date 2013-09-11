package ar.edu.utn.frba.proyecto.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import ar.edu.utn.frba.proyecto.domain.Festival;

public class FestivalDataModel extends ListDataModel<Festival> implements
		SelectableDataModel<Festival> {

	public FestivalDataModel(){
		
	}
	
	public FestivalDataModel(List<Festival> data){
		super(data);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Festival getRowData(String arg0) {
		List<Festival> festivales = (List<Festival>) getWrappedData();  
        
        for(Festival festival : festivales) { 
        	String festId = String.valueOf(festival.getId());
            if(festId.equals(arg0))  
                return festival;  
        }  
          
        return null;  
	}

	@Override
	public Object getRowKey(Festival arg0) {
		return arg0.getId();
	}

}
