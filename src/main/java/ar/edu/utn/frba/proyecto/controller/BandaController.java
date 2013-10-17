package ar.edu.utn.frba.proyecto.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.SelectableDataModel;

import ar.edu.utn.frba.proyecto.dao.impl.BandaDao;
import ar.edu.utn.frba.proyecto.domain.Banda;
import ar.edu.utn.frba.proyecto.domain.Festival;

@ManagedBean
@SessionScoped
public class BandaController extends BaseAbmController<Banda> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8390243907161766746L;
	
	@ManagedProperty("#{bandaDao}")
	private BandaDao bandaDao;

//	@Override
//	public void addItem(){
//		super.addItem();
//	}
	
	@Override
	protected void extraAddItemProcess() {
//		addDiasToFestival(getCurrentItem());
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
	
	@Override
	protected void extraGetItemProcess(Banda banda) {

	}
	
	@Override
	protected void extraRestoreOriginalItemProcess() {
		extraResetCurrentProcess();
	}
	
	@Override
	protected BandaDao getDao() {
		return this.bandaDao;
	}

	@Override
	protected Banda newBaseItem() {
		return new Banda();
	}

	@Override
	protected Banda newBaseItem(Banda item) {
		return new Banda(item);
	}

//	@Override
//	protected boolean isDifferent() {
//		return !getOriginalSelectedItem().getNombre().equals(getSelectedItem().getNombre()) ||
//				!getOriginalSelectedItem().getFechaInicio().equals(getSelectedItem().getFechaInicio()) || 
//				!getOriginalSelectedItem().getCantidadDias().equals(getSelectedItem().getCantidadDias()) ||
//				!getOriginalSelectedItem().getEstado().equals(getSelectedItem().getEstado()) ||
//				hasStateChanged();
//				
//	}

	public void setBandaDao(BandaDao bandaDao) {
		this.bandaDao = bandaDao;
	}

	@Override
	protected boolean isDifferent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected SelectableDataModel<Banda> newDataModel(List<Banda> all) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void populateBandasFromFestival(Festival festival) {
		this.bandaDao.populateBandasFromFestival(festival);
	}

	public void agregarBandaADia(Banda bandaParaAgregar, int dia, Festival festival) {
		this.bandaDao.agregarBandaADia(bandaParaAgregar, dia, festival);
		
	}

}
