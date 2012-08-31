package ar.edu.utn.frba.proyecto.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import ar.edu.utn.frba.proyecto.domain.Usuario;

public class UserDataModel extends ListDataModel<Usuario> implements
		SelectableDataModel<Usuario> {

	public UserDataModel(){
		
	}
	
	public UserDataModel(List<Usuario> data){
		super(data);
	}
	
	@Override
	public Usuario getRowData(String arg0) {
		List<Usuario> usuarios = (List<Usuario>) getWrappedData();  
        
        for(Usuario usuario : usuarios) { 
        	String analisisId = String.valueOf(usuario.getId());
            if(analisisId.equals(arg0))  
                return usuario;  
        }  
          
        return null;  
	}

	@Override
	public Object getRowKey(Usuario arg0) {
		return arg0.getId();
	}

}
