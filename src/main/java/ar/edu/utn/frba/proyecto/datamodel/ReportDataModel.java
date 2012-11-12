package ar.edu.utn.frba.proyecto.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import ar.edu.utn.frba.proyecto.domain.Reporte;

public class ReportDataModel extends ListDataModel<Reporte> implements
		SelectableDataModel<Reporte> {

	public ReportDataModel(){
		
	}
	
	public ReportDataModel(List<Reporte> reportes){
		super(reportes);
	}
	
	@Override
	public Integer getRowKey(Reporte arg0) {
		return arg0.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Reporte getRowData(String arg0) {
        List<Reporte> reportes = (List<Reporte>) getWrappedData();  
        
        for(Reporte reporte : reportes) { 
        	String reporteId = String.valueOf(reporte.getId());
            if(reporteId.equals(arg0))  
                return reporte;  
        }  
          
        return null;  
	}

}
