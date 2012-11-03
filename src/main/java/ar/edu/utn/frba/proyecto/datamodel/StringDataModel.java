package ar.edu.utn.frba.proyecto.datamodel;

import java.util.Collection;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

public class StringDataModel extends ListDataModel<String> implements
		SelectableDataModel<String> {

	public StringDataModel(){
		
	}
	
	public StringDataModel(Collection<String> data){
		super((List<String>) data);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String getRowData(String arg0) {
		List<String> stringList = (List<String>) getWrappedData();  
        
        for(String string : stringList) { 
            if(string.equals(arg0))  
                return string;  
        }  
          
        return null;  
	}

	@Override
	public Object getRowKey(String arg0) {
		return arg0;
	}

}
