package ar.edu.utn.frba.proyecto.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.SelectableDataModel;

import ar.edu.utn.frba.proyecto.dao.impl.EstadioDao;
import ar.edu.utn.frba.proyecto.domain.Asiento;
import ar.edu.utn.frba.proyecto.domain.Banda;
import ar.edu.utn.frba.proyecto.domain.Categoria;
import ar.edu.utn.frba.proyecto.domain.Dia;
import ar.edu.utn.frba.proyecto.domain.Entrada;
import ar.edu.utn.frba.proyecto.domain.Estadio;
import ar.edu.utn.frba.proyecto.domain.Factura;
import ar.edu.utn.frba.proyecto.domain.Festival;
import ar.edu.utn.frba.proyecto.domain.Fila;
import ar.edu.utn.frba.proyecto.domain.Sector;

@ViewScoped
public class EstadioController extends BaseController<Estadio> {
	
	private Double descuentoMayores;
	
	private Double descuentoMenores;
	
	private Double descuentoJubilados;

	@ManagedProperty("#{estadioDao}")
	private EstadioDao estadioDao;
	
	@ManagedProperty("#{bandaController}")
	private BandaController bandaController;

	@ManagedProperty("#{facturaController}")
	private FacturaController facturaController;
	
	private Integer cantidadElegida;

	private Integer cantidadMayores = 0;
	
	private Integer cantidadMenores = 0;
	
	private Integer cantidadJubilados = 0;

	private Festival festivalElegido;
	
	private Dia diaElegido;
	
	private Sector sectorElegido;
	
	private Fila filaElegida;
	
	private Asiento asientoElegido;

	private DualListModel<Asiento> asientosElegidos;

	private List<Entrada> entradas;
	
	@Override
	protected EstadioDao getDao() {
		return this.estadioDao;
	}
	
	public void setEstadioDao(EstadioDao estadioDao) {
		this.estadioDao = estadioDao;
	}

	@Override
	protected SelectableDataModel<Estadio> newDataModel(List<Estadio> all) {
		// TODO Auto-generated method stub
		return null;
	}

	public void resetearSeleccion() {
		this.cantidadElegida = 0;
		this.cantidadMayores = 0;
		this.cantidadMenores = 0;
		this.cantidadJubilados = 0;
		setFestivalElegido(null);
		setDiaElegido(null);
		setSectorElegido(null);
		setFilaElegida(null);
		setAsientosElegidos(null);
	}
	
	public void generarFactura(){
		if (getFestivalElegido() != null &&
			getDiaElegido() != null &&
			getSectorElegido() != null &&
			getFilaElegida() != null &&
			getAsientosElegidos() != null){

			List<Entrada> entradas = new ArrayList<Entrada>();
			int i = 0;
			for (Asiento asiento : getAsientosElegidos().getTarget()){
				i++;
				
				Categoria categoria;
				
				if (i <= getCantidadMayores()){
					categoria = new Categoria(1, "Mayor", descuentoMayores);
				} else if (i <= getCantidadMayores() + getCantidadMenores()){
					categoria = new Categoria(2, "Menor", descuentoMenores);
				} else if (i <= getCantidadMayores() + getCantidadMenores() + getCantidadJubilados()){
					categoria = new Categoria(3, "Jubilados", descuentoJubilados);
				} else{
					throw new RuntimeException("No se puede determinar la categoria de la entrada");
				}
				
				Entrada entrada = getDao().getEntrada(new Entrada(getDiaElegido(),getSectorElegido(),getFilaElegida(),asiento,categoria));
				
				getBandaController().populateBandasFromFestival(getFestivalElegido());
				
				entrada.setPrecioExtraPorBandas(getPrecioExtraForBandas(entrada));
				
				entradas.add(entrada);
				
			}
			
			setEntradas(entradas);
			
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			
			Factura factura = new Factura(getFacturaController().getLastFactura() + 1, df.format(new Date()), getEntradas());
			
			getFacturaController().setCurrentItem(factura);
			
			try {
				getFacturaController().generateBarcode();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void confirmarCompra(){
		venderEntradas(getEntradas());
		getFacturaController().addItem();
	}
	
	private void venderEntradas(List<Entrada> entradas) {
		
		for(Entrada entrada : entradas)
			getDao().venderEntrada(entrada);
		
	}

	private Double getPrecioExtraForBandas(Entrada entrada) {
		
		Double precioExtra = new Double(0);
		Dia diaEntrada = entrada.getDia();
		
		for (Dia diaFestival : getFestivalElegido().getDias()){
			if (diaEntrada.getId() == diaFestival.getId()){
				for (Banda banda : diaFestival.getBandas()){
					precioExtra = precioExtra + banda.getCostoExtra();
				}
			}
		}
		
		return precioExtra;
	}

	public List<Sector> getSectoresDisponiblesFromFestivalAndDia(Festival festival, Dia dia){
		if (festival != null && festival.getId() != null)
			return getDao().getSectoresDisponiblesFromFestivalAndDia(festival, dia);
		else
			return new ArrayList<Sector>();
	}
	
	public List<Fila> getFilasDisponiblesFromSector(Sector sector){
		if (sector != null && sector.getId() != null)
			return getDao().getFilasDisponiblesFromSector(sector);
		else
			return new ArrayList<Fila>();
	}
	
//	public List<Asiento> getAsientosDisponibles(Fila fila){
//		if (fila != null){
//			List<Asiento> result = getDao().getAsientosDisponibleFromFila(fila);
//			if (getAsientosElegidos() != null)
//				result.removeAll(getAsientosElegidos());
//			
//			return result;
//		}
//		else
//			return new ArrayList<Asiento>();
//	}

	public List<Asiento> getAsientosDisponiblesFromFila(Fila fila){
		if (fila != null)
			return getDao().getAsientosDisponibleFromFila(fila);
		else
			return new ArrayList<Asiento>();
	}

	public Sector getSectorElegido() {
		return sectorElegido;
	}

	public Fila getFilaElegida() {
		return filaElegida;
	}

	public Festival getFestivalElegido() {
		return festivalElegido;
	}
	
	public Dia getDiaElegido() {
		return diaElegido;
	}

	public void setFestivalElegido(Festival festivalElegido) {
		this.festivalElegido = festivalElegido;
		generarFactura();
	}

	public void setDiaElegido(Dia diaElegido) {
		this.diaElegido = diaElegido;
		generarFactura();
	}

	public void setSectorElegido(Sector sectorElegido) {
		this.sectorElegido = sectorElegido;
		generarFactura();
	}

	public void setFilaElegida(Fila filaElegida) {
		this.filaElegida = filaElegida;
		generarFactura();
	}

	public Integer getCantidadMayores() {
		return cantidadMayores;
	}

	public void setCantidadMayores(Integer cantidadMayores) {
		this.cantidadMayores = cantidadMayores;
		refreshCantidadElegida();
	}

	public Integer getCantidadMenores() {
		return cantidadMenores;
	}

	public void setCantidadMenores(Integer cantidadMenores) {
		this.cantidadMenores = cantidadMenores;
		refreshCantidadElegida();
	}

	public Integer getCantidadJubilados() {
		return cantidadJubilados;
	}

	public void setCantidadJubilados(Integer cantidadJubilados) {
		this.cantidadJubilados = cantidadJubilados;
		refreshCantidadElegida();
	}

	/**
	 * @return the bandaController
	 */
	public BandaController getBandaController() {
		return bandaController;
	}

	/**
	 * @param bandaController the bandaController to set
	 */
	public void setBandaController(BandaController bandaController) {
		this.bandaController = bandaController;
	}

	public List<Entrada> getEntradas() {
		return entradas;
	}

	public void setEntradas(List<Entrada> entradas) {
		this.entradas = entradas;
	}

	/**
	 * @return the facturaController
	 */
	public FacturaController getFacturaController() {
		return facturaController;
	}

	/**
	 * @param facturaController the facturaController to set
	 */
	public void setFacturaController(FacturaController facturaController) {
		this.facturaController = facturaController;
	}

	/**
	 * @return the cantidadElegida
	 */
	public Integer getCantidadElegida() {
		return cantidadElegida;
	}

	public void refreshCantidadElegida() {

		Integer cantidadElegida = getCantidadMayores() + getCantidadMenores()
				+ getCantidadJubilados();

		if (cantidadElegida > 6) {
			FacesMessage msg = new FacesMessage();
			msg.setSummary("Error de validacion");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setDetail("No puedes vender mas de 6 entradas en la misma transaccion");

			FacesContext.getCurrentInstance().addMessage(null, msg);
			setCantidadMayores(0);
			setCantidadMenores(0);
			setCantidadJubilados(0);
		} else {
			this.cantidadElegida = cantidadElegida;
		}

	}

	/**
	 * @return the asientosElegidos
	 */
	public DualListModel<Asiento> getAsientosElegidos() {
		if (this.asientosElegidos == null){
			this.asientosElegidos = new DualListModel<Asiento>(getAsientosDisponiblesFromFila(getFilaElegida()), new ArrayList<Asiento>());
		} 
//			else if (this.asientosElegidos.getSource().size() == 0)
//			this.asientosElegidos = new DualListModel<Asiento>(getAsientosDisponiblesFromFila(getFilaElegida()), getAsientosElegidos().getTarget());
		
		return asientosElegidos;
	}

	/**
	 * @param asientosElegidos the asientosElegidos to set
	 */
	public void setAsientosElegidos(DualListModel<Asiento> asientosElegidos) {
		if (asientosElegidos != null) {
			this.asientosElegidos.setSource(asientosElegidos.getSource().isEmpty() ? 
					this.asientosElegidos.getSource()
					: asientosElegidos.getSource());
			this.asientosElegidos.setTarget(asientosElegidos.getTarget().isEmpty() ? 
					this.asientosElegidos.getTarget()
					: asientosElegidos.getTarget());
		} else{
			this.asientosElegidos = null;
		}
	}

	/**
	 * @return the asientoElegido
	 */
	public Asiento getAsientoElegido() {
		return asientoElegido;
	}

	/**
	 * @param asientoElegido the asientoElegido to set
	 */
	public void setAsientoElegido(Asiento asientoElegido) {
		this.asientoElegido = asientoElegido;
	}

	public Double getDescuentoMayores() {
		return descuentoMayores;
	}

	public void setDescuentoMayores(Double descuentoMayores) {
		this.descuentoMayores = descuentoMayores;
	}

	public Double getDescuentoMenores() {
		return descuentoMenores;
	}

	public void setDescuentoMenores(Double descuentoMenores) {
		this.descuentoMenores = descuentoMenores;
	}

	public Double getDescuentoJubilados() {
		return descuentoJubilados;
	}

	public void setDescuentoJubilados(Double descuentoJubilados) {
		this.descuentoJubilados = descuentoJubilados;
	}

	public void onTransfer(TransferEvent event) {

		StringBuilder builder = new StringBuilder();

		FacesMessage msg = new FacesMessage();
		msg.setSeverity(FacesMessage.SEVERITY_INFO);

		if (event.isAdd()) {

			if (event.getItems().size() != getCantidadElegida()) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary("Cantidad incorrecta. Por favor, seleccione " + String.valueOf(getCantidadElegida()) + " asientos.");
			} else {

				for (Object item : event.getItems()) {

					Asiento asiento = (Asiento) item;
					builder.append(asiento.getNombre()).append("<br />");

					getAsientosElegidos().getTarget().add(asiento);
					getAsientosElegidos().getSource().remove(asiento);
				}
				generarFactura();
				msg.setSummary("Asientos Elegidos");
			}
		} else {
			for (Object item : event.getItems()) {
				Asiento asiento = (Asiento) item;
				builder.append(asiento.getNombre()).append("<br />");
				getAsientosElegidos().getSource().add(asiento);
				getAsientosElegidos().getTarget().remove(asiento);
			}
			msg.setSummary("Asientos Removidos");
		}

		msg.setDetail(builder.toString());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String getAsientosIds(){
		if (getEntradas() != null && !getEntradas().isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (Entrada entrada : getEntradas()) {
				sb.append(String.valueOf(entrada.getAsiento().getId()));
				sb.append(",");
			}
			
			String result = sb.toString();
			return result.substring(0, result.length() - 1);
		} else {
			return "";
		}
	}

}
