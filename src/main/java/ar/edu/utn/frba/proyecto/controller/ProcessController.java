package ar.edu.utn.frba.proyecto.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.SelectableDataModel;

import ar.edu.utn.frba.proyecto.constants.ConstantsDatatable;
import ar.edu.utn.frba.proyecto.converter.ProductConverter;
import ar.edu.utn.frba.proyecto.dao.impl.ProcessDao;
import ar.edu.utn.frba.proyecto.datamodel.ProcessDataModel;
import ar.edu.utn.frba.proyecto.domain.Lote;
import ar.edu.utn.frba.proyecto.domain.Maquinaria;
import ar.edu.utn.frba.proyecto.domain.Producto;

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
			this.items = getDao().getLotesInStatus(
					ConstantsDatatable.ESTADO_PROCESO_EN_PROCESO);
			extraGetItemsProcess();
		}

		return this.items;
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
		getCurrentItem().setProducto(getSelectedProduct());
		getCurrentItem().setMaquinaria(getMachineController().get(new Maquinaria(getSelectedMachineId())));
		super.addItem();
	}

	@Override
	protected void extraGetItemProcess(Lote lote) {
		lote.setUsuarioCreacion(getUserController().get(
				lote.getUsuarioCreacion()));
		lote.setMaquinaria(getMachineController().get(lote.getMaquinaria()));
		lote.setProducto(getProductController().get(lote.getProducto()));
	}

	public UserController getUserController() {
		return userController;
	}

	public void setUserController(UserController userController) {
		this.userController = userController;
	}
}
