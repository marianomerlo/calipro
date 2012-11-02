package ar.edu.utn.frba.proyecto.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import ar.edu.utn.frba.proyecto.domain.Lote;

public class ProcessDataModel extends ListDataModel<Lote> implements
		SelectableDataModel<Lote> {

	public ProcessDataModel(){
		
	}
	
	public ProcessDataModel(List<Lote> lotes){
		super(lotes);
	}
	
	@Override
	public Integer getRowKey(Lote arg0) {
		return arg0.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Lote getRowData(String arg0) {
        List<Lote> lotes = (List<Lote>) getWrappedData();  
        
        for(Lote lote : lotes) { 
        	String loteId = String.valueOf(lote.getId());
            if(loteId.equals(arg0))  
                return lote;  
        }  
          
        return null;  
	}

}
