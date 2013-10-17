package ar.edu.utn.frba.proyecto.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.SelectableDataModel;

import ar.edu.utn.frba.proyecto.dao.impl.EstadioDao;
import ar.edu.utn.frba.proyecto.domain.Asiento;
import ar.edu.utn.frba.proyecto.domain.Banda;
import ar.edu.utn.frba.proyecto.domain.Dia;
import ar.edu.utn.frba.proyecto.domain.Entrada;
import ar.edu.utn.frba.proyecto.domain.Estadio;
import ar.edu.utn.frba.proyecto.domain.Factura;
import ar.edu.utn.frba.proyecto.domain.Festival;
import ar.edu.utn.frba.proyecto.domain.Fila;
import ar.edu.utn.frba.proyecto.domain.Sector;

@ViewScoped
public class EstadioController extends BaseController<Estadio> {

	@ManagedProperty("#{estadioDao}")
	private EstadioDao estadioDao;
	
	@ManagedProperty("#{bandaController}")
	private BandaController bandaController;

	@ManagedProperty("#{facturaController}")
	private FacturaController facturaController;
	
	private Festival festivalElegido;
	
	private Dia diaElegido;
	
	private Sector sectorElegido;
	
	private Fila filaElegida;
	
	private Asiento asientoElegido;
	
	private Entrada entrada;
	
	
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
		setFestivalElegido(null);
		setDiaElegido(null);
		setSectorElegido(null);
		setFilaElegida(null);
		setAsientoElegido(null);
	}
	
	public void generarFactura(){
		if (getFestivalElegido() != null &&
			getDiaElegido() != null &&
			getSectorElegido() != null &&
			getFilaElegida() != null &&
			getAsientoElegido() != null){
			
			Entrada entrada = getDao().getEntrada(new Entrada(getDiaElegido(),getSectorElegido(),getFilaElegida(),getAsientoElegido()));
			
			getBandaController().populateBandasFromFestival(getFestivalElegido());
			
			entrada.setPrecioExtraPorBandas(getPrecioExtraForBandas(entrada));
			
			setEntrada(entrada);
			
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			
			Factura factura = new Factura(getFacturaController().getLastFactura() + 1, df.format(new Date()), entrada.getPrecioBase() + entrada.getPrecioExtraPorBandas());
			
			getFacturaController().setCurrentItem(factura);
		}
	}
	
	public void confirmarCompra(){
		venderEntrada(getEntrada());
		getFacturaController().addItem();
	}
	
	private void venderEntrada(Entrada entrada) {
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
	
	public List<Asiento> getAsientosDisponibleFromFila(Fila fila){
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

	public Asiento getAsientoElegido() {
		return asientoElegido;
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

	public void setAsientoElegido(Asiento asientoElegido) {
		this.asientoElegido = asientoElegido;
		generarFactura();
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

	/**
	 * @return the entrada
	 */
	public Entrada getEntrada() {
		return entrada;
	}

	/**
	 * @param entrada the entrada to set
	 */
	public void setEntrada(Entrada entrada) {
		this.entrada = entrada;
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

}
