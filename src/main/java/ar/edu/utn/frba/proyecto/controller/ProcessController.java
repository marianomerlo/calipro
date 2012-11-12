package ar.edu.utn.frba.proyecto.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.SelectableDataModel;

import ar.edu.utn.frba.proyecto.constants.ConstantsDatatable;
import ar.edu.utn.frba.proyecto.dao.impl.ProcessDao;
import ar.edu.utn.frba.proyecto.datamodel.ProcessDataModel;
import ar.edu.utn.frba.proyecto.domain.Analisis;
import ar.edu.utn.frba.proyecto.domain.Lote;
import ar.edu.utn.frba.proyecto.domain.Maquinaria;
import ar.edu.utn.frba.proyecto.domain.Message;
import ar.edu.utn.frba.proyecto.domain.Paso;
import ar.edu.utn.frba.proyecto.domain.Producto;
import ar.edu.utn.frba.proyecto.domain.enumType.StatusType;

@ManagedBean
@SessionScoped
public class ProcessController extends BaseAbmController<Lote> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3724027314179697414L;

	private List<Maquinaria> availableMachines;

	private Integer selectedMachineId = 0;

	private Producto selectedProduct = new Producto();

	@ManagedProperty("#{processDao}")
	private ProcessDao processDao;

	public ProcessDao getProcessDao() {
		return processDao;
	}

	public void setProcessDao(ProcessDao processDao) {
		this.processDao = processDao;
	}

	@ManagedProperty("#{machineController}")
	private MachineController machineController;

	@ManagedProperty("#{productController}")
	private ProductController productController;

	@ManagedProperty("#{userController}")
	private UserController userController;

	@ManagedProperty("#{analisisController}")
	private AnalisisController analisisController;

	public List<Producto> filterProducts(String query) {
		List<Producto> resultList = new ArrayList<Producto>();

		List<Producto> productList = getProductController().getItems();
		for (Producto producto : productList) {
			if (producto.getNombre().startsWith(query)) {
				resultList.add(producto);
			}
		}

		return resultList;
	}

	public List<Maquinaria> getAvailableMachines() {
		this.availableMachines = getMachineController().getAvailableMachines();
		return availableMachines;
	}

	@Override
	public List<Lote> getItems() {
		if (this.items == null) {
			refreshItems();
		}

		return this.items;
	}

	@Override
	public void refreshItems() {
		setItems(getDao().getLotesInStatus(
				ConstantsDatatable.ESTADO_PROCESO_EN_PROCESO));
		extraGetItemsProcess();
		setDataModel(newDataModel(getItems()));
	}

	private List<Paso> pasosLote;

	public void setPasosLote(List<Paso> pasosLote) {
		this.pasosLote = pasosLote;
	}

	public List<Paso> getPasosLote() {
		if (pasosLote == null || pasosLote.size() == 0) {
			refreshPasos();
		}

		return pasosLote;
	}

	public void setLoteAndRefreshPasos(Lote lote) {
		setSelectedItem(lote);
		refreshPasos();
	}

	public void refreshPasos() {
		if (getSelectedItem() != null && getSelectedItem().getId() != null) {
			List<Paso> pasos = getDao().getPasosLote(getSelectedItem());
			for (Paso paso : pasos) {
				paso.setAnalisis(getAnalisisController()
						.getAnalisisByPasoProceso(paso,getSelectedItem().getId()));
			}
			pasosLote = pasos;
		}
	}

	/**
	 * @param availableMachines
	 *            the availableMachines to set
	 */
	public void setAvailableMachines(List<Maquinaria> availableMachines) {
		this.availableMachines = availableMachines;
	}

	/**
	 * @return the selectedMachineId
	 */
	public Integer getSelectedMachineId() {
		return selectedMachineId;
	}

	/**
	 * @param selectedMachineId
	 *            the selectedMachineId to set
	 */
	public void setSelectedMachineId(Integer selectedMachineId) {
		this.selectedMachineId = selectedMachineId;
	}

	/**
	 * @return the machineController
	 */
	public MachineController getMachineController() {
		return machineController;
	}

	/**
	 * @param machineController
	 *            the machineController to set
	 */
	public void setMachineController(MachineController machineController) {
		this.machineController = machineController;
	}

	@Override
	protected ProcessDao getDao() {
		return processDao;
	}

	/**
	 * @return the productController
	 */
	public ProductController getProductController() {
		return productController;
	}

	/**
	 * @param productController
	 *            the productController to set
	 */
	public void setProductController(ProductController productController) {
		this.productController = productController;
	}

	/**
	 * @return the selectedProduct
	 */
	public Producto getSelectedProduct() {
		return selectedProduct;
	}

	/**
	 * @param selectedProduct
	 *            the selectedProduct to set
	 */
	public void setSelectedProduct(Producto selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	@Override
	protected Lote newBaseItem() {
		return new Lote();
	}

	@Override
	protected Lote newBaseItem(Lote item) {
		return new Lote(item);
	}

	@Override
	protected boolean isDifferent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected SelectableDataModel<Lote> newDataModel(List<Lote> all) {
		return new ProcessDataModel(all);
	}

	@Override
	public void addItem() {

		if (getSelectedMachineId() == 0) {
			String confirmMessage = "La maquina seleccionada no es válida";
			FacesContext.getCurrentInstance().addMessage(
					"addLoteGrowlMessagesKeys",
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							confirmMessage, null));
		} else if (getSelectedProduct().getNombre() == null) {
			String confirmMessage = "El producto seleccionado no es válido";
			FacesContext.getCurrentInstance().addMessage(
					"addLoteGrowlMessagesKeys",
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							confirmMessage, null));
		} else {
			getCurrentItem().setProducto(getSelectedProduct());
			getCurrentItem().setMaquinaria(
					getMachineController().get(
							new Maquinaria(getSelectedMachineId())));
			super.addItem();
		}
	}

	public void extraAddItemProcess() {
		String key = "addLoteGrowlMessagesKeys";
		if (FacesContext.getCurrentInstance().getMessageList(key).size() == 0) {
			String confirmMessage = ITEM_NAME + " creado satisfactoriamente";
			FacesContext.getCurrentInstance().addMessage(
					key,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							confirmMessage, null));
		}

	}

	public void resetCurrent() {
		setSelectedMachineId(0);
		setSelectedProduct(new Producto());
	}

	@Override
	protected void extraGetItemProcess(Lote lote) {
		lote.setUsuarioCreacion(getUserController().get(
				lote.getUsuarioCreacion()));
		lote.setUsuarioUltimaModificacion(getUserController().get(
				lote.getUsuarioUltimaModificacion()));
		lote.setMaquinaria(getMachineController().get(lote.getMaquinaria()));
		lote.setProducto(getProductController().get(lote.getProducto()));
	}

	public UserController getUserController() {
		return userController;
	}

	public void setUserController(UserController userController) {
		this.userController = userController;
	}

	public void endProcess() {
		Message message = getDao().endProcess(getSelectedItem());

		FacesContext
				.getCurrentInstance()
				.addMessage(
						"endProcessGrowlMessagesKeys",
						new FacesMessage(

								StatusType.ERROR.equals(message.getStatus()) ? FacesMessage.SEVERITY_ERROR
										: FacesMessage.SEVERITY_INFO, message
										.getMessage(), null));

		refreshItems();

	}

	public void cancelProcess() {
		Message message = getDao().cancelProcess(getSelectedItem());

		FacesContext
				.getCurrentInstance()
				.addMessage(
						"endProcessGrowlMessagesKeys",
						new FacesMessage(

								StatusType.ERROR.equals(message.getStatus()) ? FacesMessage.SEVERITY_ERROR
										: FacesMessage.SEVERITY_INFO, message
										.getMessage(), null));

		refreshItems();
	}

	public void askAnalisis() {
		Message message = getDao().askAnalisis(getLoteToAsk(),getPasoToAsk(),getAnalisisToAsk());
		
		FacesContext
		.getCurrentInstance()
		.addMessage(
				"generalProcessGrowlMessagesKeys",
				new FacesMessage(

						StatusType.ERROR.equals(message.getStatus()) ? FacesMessage.SEVERITY_ERROR
								: FacesMessage.SEVERITY_INFO, message
								.getMessage(), null));
		
		refreshPasos();
	}

	public void prepareToAsk(Lote lote, Paso paso,Analisis analisis) {
		setLoteToAsk(lote);
		setAnalisisToAsk(analisis);
		setPasoToAsk(paso);
	}
	
	private Lote loteToAsk;
	
	private Analisis analisisToAsk;

	private Paso pasoToAsk;
	
	/**
	 * @return the analisisController
	 */
	public AnalisisController getAnalisisController() {
		return analisisController;
	}

	/**
	 * @param analisisController
	 *            the analisisController to set
	 */
	public void setAnalisisController(AnalisisController analisisController) {
		this.analisisController = analisisController;
	}

	/**
	 * @return the loteToAsk
	 */
	public Lote getLoteToAsk() {
		return loteToAsk;
	}

	/**
	 * @param loteToAsk the loteToAsk to set
	 */
	public void setLoteToAsk(Lote loteToAsk) {
		this.loteToAsk = loteToAsk;
	}

	/**
	 * @return the analisisToAsk
	 */
	public Analisis getAnalisisToAsk() {
		return analisisToAsk;
	}

	/**
	 * @param analisisToAsk the analisisToAsk to set
	 */
	public void setAnalisisToAsk(Analisis analisisToAsk) {
		this.analisisToAsk = analisisToAsk;
	}

	/**
	 * @return the pasoToAsk
	 */
	public Paso getPasoToAsk() {
		return pasoToAsk;
	}

	/**
	 * @param pasoToAsk the pasoToAsk to set
	 */
	public void setPasoToAsk(Paso pasoToAsk) {
		this.pasoToAsk = pasoToAsk;
	}
}
