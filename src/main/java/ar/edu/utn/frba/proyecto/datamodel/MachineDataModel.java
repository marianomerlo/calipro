package ar.edu.utn.frba.proyecto.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import ar.edu.utn.frba.proyecto.domain.Maquinaria;

public class MachineDataModel extends ListDataModel<Maquinaria> implements
		SelectableDataModel<Maquinaria> {

	public MachineDataModel(){
		
	}
	
	public MachineDataModel(List<Maquinaria> data){
		super(data);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Maquinaria getRowData(String arg0) {
		List<Maquinaria> machineList = (List<Maquinaria>) getWrappedData();  
        
        for(Maquinaria maquina : machineList) { 
        	String maquinaId = String.valueOf(maquina.getId());
            if(maquinaId.equals(arg0))  
                return maquina;  
        }  
          
        return null;  
	}

	@Override
	public Object getRowKey(Maquinaria arg0) {
		return arg0.getId();
	}

}
