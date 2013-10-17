package ar.edu.utn.frba.proyecto.controller;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.SelectableDataModel;

import ar.edu.utn.frba.proyecto.dao.impl.FestDao;
import ar.edu.utn.frba.proyecto.datamodel.FestivalDataModel;
import ar.edu.utn.frba.proyecto.domain.Banda;
import ar.edu.utn.frba.proyecto.domain.Dia;
import ar.edu.utn.frba.proyecto.domain.Estado;
import ar.edu.utn.frba.proyecto.domain.Festival;

@ManagedBean
@SessionScoped
public class FestivalController extends BaseAbmController<Festival> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8390243907161766746L;
	
	@ManagedProperty("#{festDao}")
	private FestDao festDao;

	@ManagedProperty("#{bandaController}")
	private BandaController bandaController;

	@ManagedProperty("#{estadoController}")
	private EstadoController estadoController;

	private List<Estado> availableStates;
	
	private List<Banda> bandasDisponibles;

	private Banda bandaParaAgregar = new Banda();
	
	private Integer selectedItemEstadoId = 0;
	
	private int dayIndexTab = 1;
	
	private String costoExtra = "0";
	
	private String tiempoAsignado = "0";
	
//	@Override
//	public void addItem(){
//		super.addItem();
//	}
	
	@Override
	protected void extraAddItemProcess() {
		addDiasToFestival(getCurrentItem());
		addEstadioToFestival(getCurrentItem());
		super.extraAddItemProcess();
	}
	
//	@Override
//	protected void extraUpdateItemProcess() {
//		
//		if (hasProfilesChanged()) {
//			if (getSelectedProfiles().length > 0) {
//				getProfileController().removeProfilesFromUser(getSelectedItem());
//				getProfileController().addProfilesToUser(getSelectedItem(),getSelectedProfiles());
//			} else {
//				String confirmMessage = "Debes seleccionar al menos un perfil";
//				FacesContext.getCurrentInstance().addMessage(getUpdateMessageKey(),
//						new FacesMessage(FacesMessage.SEVERITY_FATAL,
//								confirmMessage, null));
//				return;
//			}
//		}
//		super.extraUpdateItemProcess();
//	}
	
	private void addEstadioToFestival(Festival currentItem) {
		getDao().addEstadioToFestival(currentItem);
		
	}

	private void addDiasToFestival(Festival currentItem) {
		getDao().addDiasToFestival(currentItem);
	}

	@Override
	protected void extraGetItemProcess(Festival festival) {
		festival.setDias(getDiasFromFestival(festival));
		populateBandasFromFestival(festival);
	}
	
	private void populateBandasFromFestival(Festival festival) {
		this.bandaController.populateBandasFromFestival(festival);
	}

	private List<Dia> getDiasFromFestival(Festival festival) {
		return getDao().getDiasByFestival(festival);
	}

	@Override
	protected void extraRestoreOriginalItemProcess() {
		extraResetCurrentProcess();
	}
	
	@Override
	protected FestDao getDao() {
		return this.festDao;
	}

	@Override
	protected Festival newBaseItem() {
		return new Festival();
	}

	@Override
	protected Festival newBaseItem(Festival item) {
		return new Festival(item);
	}

	@Override
	protected boolean isDifferent() {
		return !getOriginalSelectedItem().getNombre().equals(getSelectedItem().getNombre()) ||
				!getOriginalSelectedItem().getFechaInicio().equals(getSelectedItem().getFechaInicio()) || 
				!getOriginalSelectedItem().getCantidadDias().equals(getSelectedItem().getCantidadDias()) ||
				!getOriginalSelectedItem().getEstado().equals(getSelectedItem().getEstado()) ||
				hasStateChanged();
				
	}

	private boolean hasStateChanged() {
		return !(getOriginalSelectedItem().getEstado().getId() == getSelectedItem().getEstado().getId());
	}

	public void setFestDao(FestDao festDao) {
		this.festDao = festDao;
	}

	public void setBandaController(BandaController bandaController) {
		this.bandaController = bandaController;
	}

	@Override
	protected SelectableDataModel<Festival> newDataModel(List<Festival> all) {
		return new FestivalDataModel(all);
	}

	public EstadoController getEstadoController() {
		return estadoController;
	}

	public void setEstadoController(EstadoController estadoController) {
		this.estadoController = estadoController;
	}

	/**
	 * @return the availableStates
	 */
	public List<Estado> getAvailableStates() {
		if ( this.availableStates == null){
			this.availableStates = getEstadoController().getEstadosFromElement(getSelectedItem());
			
		}
		return availableStates;
	}

	/**
	 * @param availableStates the availableStates to set
	 */
	public void setAvailableStates(List<Estado> availableStates) {
		this.availableStates = availableStates;
	}

	/**
	 * @return the selectedItemEstado
	 */
	public Integer getSelectedItemEstadoId() {
		if ( this.selectedItemEstadoId == null){
			this.selectedItemEstadoId = getSelectedItem().getEstado().getId();
		}
		return selectedItemEstadoId;
	}

	/**
	 * @param selectedItemEstado the selectedItemEstado to set
	 */
	public void setSelectedItemEstadoId(Integer selectedItemEstadoId) {
		this.selectedItemEstadoId = selectedItemEstadoId;
	}
	
	public Festival getByUnique(Festival festival){
		return getDao().getByUnique(festival);
	}

	/**
	 * @return the bandasDisponibles
	 */
	public List<Banda> getBandasDisponibles() {
		if (this.bandasDisponibles == null){
			this.bandasDisponibles = this.bandaController.getItems();
			this.bandasDisponibles.removeAll(getSelectedItem().getBandasFromFestival());
		}
		
		return bandasDisponibles;
	}

	/**
	 * @param bandasDisponibles the bandasDisponibles to set
	 */
	public void setBandasDisponibles(List<Banda> bandasDisponibles) {
		this.bandasDisponibles = bandasDisponibles;
	}
	
	public void refreshBandasDisponibles(){
		this.bandaController.refreshItems();
		this.bandasDisponibles = this.bandaController.getItems();
		this.bandasDisponibles.removeAll(getSelectedItem().getBandasFromFestival());
		this.bandasDisponibles.remove(getBandaParaAgregar());
	}

	/**
	 * @return the bandasParaAgregar
	 */
	public Banda getBandaParaAgregar() {
		return bandaParaAgregar;
	}

	/**
	 * @param bandasParaAgregar the bandasParaAgregar to set
	 */
	public void setBandaParaAgregar(Banda bandaParaAgregar) {
		this.bandaParaAgregar = bandaParaAgregar;
	}
	
	public void agregarBanda(){
		getBandaParaAgregar().setCostoExtra(new Double(costoExtra));
		getBandaParaAgregar().setTiempoAsignado(tiempoAsignado);
		this.bandaController.agregarBandaADia(getBandaParaAgregar(), getDayIndexTab() , getSelectedItem());
		refreshBandasDisponibles();
	}
	
	public final void onTabChange(final TabChangeEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> params = context.getExternalContext()
				.getRequestParameterMap();
		TabView tabView = (TabView) event.getComponent();
		String activeIndexValue = params.get(tabView.getClientId(context)
				+ "_tabindex");
		setDayIndexTab(Integer.parseInt(activeIndexValue) + 1);
	}

	private void setDayIndexTab(int day) {
		this.dayIndexTab = day;
		
	}
	
	private int getDayIndexTab(){
		return this.dayIndexTab;
	}

	/**
	 * @return the costoExtra
	 */
	public String getCostoExtra() {
		return costoExtra;
	}

	/**
	 * @param costoExtra the costoExtra to set
	 */
	public void setCostoExtra(String costoExtra) {
		this.costoExtra = costoExtra;
	}

	/**
	 * @return the tiempoAsignado
	 */
	public String getTiempoAsignado() {
		return tiempoAsignado;
	}

	/**
	 * @param tiempoAsignado the tiempoAsignado to set
	 */
	public void setTiempoAsignado(String tiempoAsignado) {
		this.tiempoAsignado = tiempoAsignado;
	}
	
}
