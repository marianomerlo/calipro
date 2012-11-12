package ar.edu.utn.frba.proyecto.controller;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.SelectableDataModel;

import ar.edu.utn.frba.proyecto.dao.Dao;
import ar.edu.utn.frba.proyecto.dao.impl.ReportDao;
import ar.edu.utn.frba.proyecto.datamodel.ReportDataModel;
import ar.edu.utn.frba.proyecto.domain.Estado;
import ar.edu.utn.frba.proyecto.domain.Reporte;

@ManagedBean
@SessionScoped
public class ReportController extends BaseController<Reporte> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3722529046134570898L;
	
	@ManagedProperty("#{reportDao}")
	private ReportDao reportDao;
	
	private Reporte selectedItem;
	
	private Integer idProducto;
	
	private String nombreProducto;
	
	private Integer idUsuario;
	
	private String nombreUsuario;
	
	private String nombreAnalisis;
	
	private Estado estado;
	
	private Date fechaInicio;
	
	private Date fechaFin;

	@Override
	protected ReportDao getDao() {
		return this.reportDao;
	}
	
	public void resetValues(){
		this.idProducto = null;
		this.nombreProducto = null;
		this.idUsuario = null;
		this.nombreUsuario = null;
		this.nombreAnalisis = null;
		this.estado = null;
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
			
			switch(reportId){
			//Recibe idproducto, nombreusuario, idusuario, nombreanalisis, fechaDesde, fechaHasta
			case 1:	getDao().getReporteCantidadAnalisisUsuario();
					break;
			
			//Recibe idproducto, nombreusuario, idusuario, nombreanalisis, fechaDesde, fechaHasta
			case 2: getDao().getReporteEstadosProductosProcesadios();
					break;
				
			case 3:	getDao().getReporteLotesProducto();
					break;
				
			case 4:	getDao().getReporteTiempoPromedioProducto();
					break;
			}
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

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
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

}
