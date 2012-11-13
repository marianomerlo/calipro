package ar.edu.utn.frba.proyecto.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.event.DateSelectEvent;
import org.primefaces.model.SelectableDataModel;

import ar.edu.utn.frba.proyecto.dao.impl.ReportDao;
import ar.edu.utn.frba.proyecto.datamodel.ReportDataModel;
import ar.edu.utn.frba.proyecto.domain.Estado;
import ar.edu.utn.frba.proyecto.domain.Lote;
import ar.edu.utn.frba.proyecto.domain.Reporte;
import ar.edu.utn.frba.proyecto.domain.ReporteContainer;

@ManagedBean
@RequestScoped
public class ReportController extends BaseController<Reporte> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3722529046134570898L;
	
	@ManagedProperty("#{reportDao}")
	private ReportDao reportDao;
	
	@ManagedProperty("#{estadoController}")
	private EstadoController estadoController;
	
	private Reporte selectedItem;

	private List<ReporteContainer> report;
	
	private String idProducto;
	
	private String nombreProducto;
	
	private String idUsuario;
	
	private String nombreUsuario;
	
	private String nombreAnalisis;
	
	private Integer estado;
	
	private Date fechaInicio;
	
	private Date fechaFin;
	
	private String ALL_VALUES = "*";
	
	private String dateFormat = "ddMMyyyy";

	@Override
	protected ReportDao getDao() {
		return this.reportDao;
	}
	
	public void resetValues(){
		this.idProducto = ALL_VALUES;
		this.nombreProducto = ALL_VALUES;
		this.idUsuario = ALL_VALUES;
		this.nombreUsuario = ALL_VALUES;
		this.nombreAnalisis = ALL_VALUES;
		this.estado = 0;
		this.fechaInicio = null;
		this.fechaFin = null;
	}

	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}

	@Override
	protected SelectableDataModel<Reporte> newDataModel(List<Reporte> all) {
		return new ReportDataModel(all);
	}
	
	public void generateReport(){
			Integer reportId = getSelectedItem().getId();
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			
			Integer currentIdProducto = ALL_VALUES.equals(getIdProducto()) ? null : Integer.valueOf(getIdProducto());
			String currentNombreProduct = ALL_VALUES.equals(getNombreProducto()) ? "''" : "'" + getNombreProducto() + "'";
			Integer currentIdUsuario = ALL_VALUES.equals(getIdUsuario()) ? null : Integer.valueOf(getIdUsuario());
			String currentNombreUsuario = ALL_VALUES.equals(getNombreUsuario()) ? "''" : "'" + getNombreUsuario() + "'";
			String currentNombreAnalisis = ALL_VALUES.equals(getNombreAnalisis()) ? "''" : "'" + getNombreAnalisis() + "'";
			Integer currentEstado = 0 == getEstado() ? null : getEstado();
			
			String fechaInicioStr = "'" + sdf.format(fechaInicio) + "'";
			String fechaFinStr = "'" + sdf.format(fechaFin) + "'";
			
			List<ReporteContainer> result;
			
			switch(reportId){
			//Recibe nombreusuario, idusuario, nombreanalisis, fechaDesde, fechaHasta
			case 1:	result = getDao().getReporteCantidadAnalisisUsuario( currentNombreUsuario, 
																currentIdUsuario, 
																currentNombreAnalisis, 
																fechaInicioStr, 
																fechaFinStr );
					break;
			
			//Recibe idproducto, nombreproducto, estado, fechaDesde, fechaHasta
			case 2: result = getDao().getReporteEstadosProductosProcesados(currentIdProducto,
																	currentNombreUsuario,
																	currentEstado,
																	fechaInicioStr,
																	fechaFinStr);
					break;
				
			//Recibe idproducto, nombreproducto, fechaDesde, fechaHasta
			case 3:	result = getDao().getReporteLotesProducto(currentIdProducto,
														currentNombreProduct,
														fechaInicioStr,
														fechaFinStr);
					break;
			
			//Recibe fechaDesde, fechaHasta		
			case 4:	result = getDao().getReporteTiempoPromedioProducto(fechaInicioStr,fechaFinStr);
					break;
					
			default: result = new ArrayList<ReporteContainer>();
			}
			
			setReport(result);
	}
	
	public List<Estado> loteStates(){
		return getEstadoController().getEstadosFromElement(new Lote());
	}
	
    public void handleStartDateSelect(DateSelectEvent event) {  
    	setFechaInicio(event.getDate());
    }  

    public void handleEndDateSelect(DateSelectEvent event) {  
    	setFechaFin(event.getDate());
    }  
	

	/**
	 * @return the selectedItem
	 */
	public Reporte getSelectedItem() {
		return selectedItem;
	}

	/**
	 * @param selectedItem the selectedItem to set
	 */
	public void setSelectedItem(Reporte selectedItem) {
		this.selectedItem = selectedItem;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getNombreAnalisis() {
		return nombreAnalisis;
	}

	public void setNombreAnalisis(String nombreAnalisis) {
		this.nombreAnalisis = nombreAnalisis;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public ReportDao getReportDao() {
		return reportDao;
	}

	public String getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the estado
	 */
	public Integer getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	/**
	 * @return the estadoController
	 */
	public EstadoController getEstadoController() {
		return estadoController;
	}

	/**
	 * @param estadoController the estadoController to set
	 */
	public void setEstadoController(EstadoController estadoController) {
		this.estadoController = estadoController;
	}

	/**
	 * @return the report
	 */
	public List<ReporteContainer> getReport() {
		return report;
	}

	/**
	 * @param report the report to set
	 */
	public void setReport(List<ReporteContainer> report) {
		this.report = report;
	}

}
