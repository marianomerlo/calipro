package ar.edu.utn.frba.proyecto.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.primefaces.model.SelectableDataModel;

import ar.edu.utn.frba.proyecto.dao.impl.PasoDao;
import ar.edu.utn.frba.proyecto.domain.Criterio;
import ar.edu.utn.frba.proyecto.domain.Paso;
import ar.edu.utn.frba.proyecto.domain.Producto;

@ManagedBean
@ApplicationScoped
public class PasoController extends BaseAbmController<Paso> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2735492590538830198L;

	private Producto producto;
	
	private String[] expectedValues;
	
	@ManagedProperty("#{productController}")
	private ProductController productController;

	@ManagedProperty("#{analisisController}")
	private AnalisisController analisisController;
	
	@ManagedProperty("#{pasoDao}")
	private PasoDao pasoDao;
	
	@Override
	protected PasoDao getDao() {
		return this.pasoDao;
	}
	
	public void setPasoDao(PasoDao pasoDao) {
		this.pasoDao = pasoDao;
	}

	@Override
	protected Paso newBaseItem() {
		return new Paso();
	}

	@Override
	protected Paso newBaseItem(Paso item) {
		return new Paso(item);
	}
	
	@Override
	public void addItem(){
		getCurrentItem().setProductoId(getProductController().getSelectedItem().getId());
		getCurrentItem().setVersion(getProductController().getProductVersion(getProductController().getSelectedItem()));
		super.addItem();
	}

	@Override
	public void deleteItems(){
		if ( getSelectedItems() == null)
			selectedItems = new Paso[1];
		
		getSelectedItem().setUsuarioCreacion(getSessionController().getLoggedUser());
		getSelectedItems()[0] = getSelectedItem();
		
		super.deleteItems();
	}
	
	@Override
	public List<Paso> getItems() {
		List<Paso> pasos = getDao().getPasosByProduct(getProductController().getSelectedItem());
		
		for (Paso paso : pasos){
			paso.setAnalisis(getAnalisisController().getAnalisisByPaso(paso));
		}
		
		return pasos;
	}

	@Override
	protected boolean isDifferent() {
		return !getOriginalSelectedItem().getDescripcion().equals(getSelectedItem().getDescripcion()) ||
				hasAnalisisChanged(); 
	}
	
	public void addAnalisisToPaso(){
		getSelectedItem().setUsuarioCreacion(getSessionController().getLoggedUser());
		
		getAnalisisController().getSelectedAnalisis().setCriterios(getCriterioValuesAsList());
		
		getDao().addAnalisisToPaso(getSelectedItem(), getAnalisisController().getSelectedAnalisis());
	}
	
	private List<Criterio> getCriterioValuesAsList() {
		List<Criterio> resultList = new ArrayList<Criterio>();
		int index = 0;
		for (Criterio entry : getAnalisisController().getRefreshedCriterios()){
			Criterio tempCri = new Criterio(entry);
			tempCri.setValorEsperado(getExpectedValues()[index]);
			resultList.add(tempCri);
			index++;
		}
		return resultList;
	}

	private boolean hasAnalisisChanged() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected SelectableDataModel<Paso> newDataModel(List<Paso> all) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the producto
	 */
	public Producto getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	/**
	 * @return the productController
	 */
	public ProductController getProductController() {
		return productController;
	}

	/**
	 * @param productController the productController to set
	 */
	public void setProductController(ProductController productController) {
		this.productController = productController;
	}
	
	/**
	 * @return the expectedValues
	 */
	public String[] getExpectedValues() {
		if ( this.expectedValues == null || this.expectedValues.length == 0){
			this.expectedValues = new String[50];
		}
		
		return expectedValues;
	}

	/**
	 * @param expectedValues the expectedValues to set
	 */
	public void setExpectedValues(String[] expectedValues) {
		this.expectedValues = expectedValues;
	}

	/**
	 * @return the analisisController
	 */
	public AnalisisController getAnalisisController() {
		return analisisController;
	}

	/**
	 * @param analisisController the analisisController to set
	 */
	public void setAnalisisController(AnalisisController analisisController) {
		this.analisisController = analisisController;
	}

}
