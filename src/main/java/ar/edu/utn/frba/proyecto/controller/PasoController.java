package ar.edu.utn.frba.proyecto.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.model.SelectableDataModel;

import ar.edu.utn.frba.proyecto.dao.impl.PasoDao;
import ar.edu.utn.frba.proyecto.domain.Analisis;
import ar.edu.utn.frba.proyecto.domain.Criterio;
import ar.edu.utn.frba.proyecto.domain.Paso;
import ar.edu.utn.frba.proyecto.domain.Producto;
import ar.edu.utn.frba.proyecto.domain.enumType.CriterioType;

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
	public void addItem() {
		getCurrentItem().setProductoId(
				getProductController().getSelectedItem().getId());
		getCurrentItem().setVersion(
				getProductController().getProductVersion(
						getProductController().getSelectedItem()));
		super.addItem();
	}

	@Override
	public void extraAddItemProcess() {
		refreshItems();
		super.extraAddItemProcess();
	}

	@Override
	public void deleteItems() {
		if (getSelectedItems() == null)
			selectedItems = new Paso[1];

		getSelectedItem().setUsuarioCreacion(
				getSessionController().getLoggedUser());
		getSelectedItems()[0] = getSelectedItem();

		super.deleteItems();
	}

	private List<Paso> pasos;

	public void refreshItems() {
		setPasos(getDao().getPasosByProduct(
				getProductController().getSelectedItem()));

		for (Paso paso : getPasos()) {
			List<Analisis> firstAnalisisList = getAnalisisController()
					.getAnalisisByPaso(paso);
			
			for (Analisis analisis : firstAnalisisList){
				for ( Criterio criterio : analisis.getCriterios()){
					List<String> values = getAnalisisController().getCriterioController().getValuesFromCriterio(criterio);
					if ( values.size() > 0){
						criterio.setTipo(CriterioType.COMBO);
						criterio.setOpciones(values);
					}else{
						criterio.setTipo(CriterioType.TEXTO);
					}
				}
			}
			// for (Analisis analisis : firstAnalisisList) {
			// analisis.setNombre(getAnalisisController().get(analisis).getNombre());
			//
			// for(Criterio criterio : analisis.getCriterios()){
			// criterio.setNombre(getAnalisisController().getCriterioController().get(criterio).getNombre());
			// }
			// }

			paso.setAnalisis(firstAnalisisList);
		}

	}

	public void setProductAndRefreshPasos(Producto producto) {
		getProductController().setSelectedItem(producto);
		refreshItems();
	}

	@Override
	public List<Paso> getItems() {
		if (getPasos() == null || getPasos().size() == 0) {
			refreshItems();
		}

		return getPasos();
	}

	@Override
	protected boolean isDifferent() {
		return !getOriginalSelectedItem().getDescripcion().equals(
				getSelectedItem().getDescripcion());
	}

	public void addAnalisisToPaso() {
		getSelectedItem().setUsuarioCreacion(
				getSessionController().getLoggedUser());

		getAnalisisController().getSelectedAnalisis().setCriterios(
				getCriterioValuesAsList());

		getDao().addAnalisisToPaso(getSelectedItem(),
				getAnalisisController().getSelectedAnalisis());

		resetAnalisisToPasoForm();
		refreshItems();

		FacesContext.getCurrentInstance().addMessage(
				"addAnalisisToPasoGrowlMessageKeys",
				new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Analisis agregado satisfactoriamente", null));
	}

	public void updateAnalisisToPaso() {
		if (isReallyDifferent()) {
			getSelectedItem().setUsuarioUltimaModificacion(
					getSessionController().getLoggedUser());

			getAnalisisController().getSelectedAnalisis().setCriterios(
					getCriterioValuesAsList());

			getDao().updateAnalisisToPaso(getSelectedItem(),
					getAnalisisController().getSelectedAnalisis());

			refreshItems();

			FacesContext.getCurrentInstance().addMessage(
					"updateAnalisisToPasoGrowlMessageKeys",
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Analisis modificado satisfactoriamente", null));
		} else {
			String errorMessage = "No ha realizado modificaciones";
			FacesContext.getCurrentInstance().addMessage(
					"updateAnalisisToPasoGrowlMessageKeys",
					new FacesMessage(FacesMessage.SEVERITY_WARN, errorMessage,
							null));
		}

	}
	
	public void deleteAnalisisToPaso() {
			getSelectedItem().setUsuarioUltimaModificacion(
					getSessionController().getLoggedUser());
			
			getDao().deleteAnalisisToPaso(getSelectedItem(),
					getAnalisisController().getSelectedAnalisis());
			
			refreshItems();
			
	}

	private boolean isReallyDifferent() {
		Analisis original = getAnalisisController().getOriginalSelectedItem();
		Analisis modified = getAnalisisController().getSelectedAnalisis();

		if (!modified.getNombre().equals(original.getNombre())
				|| original.getCriterios().size() != modified.getCriterios()
						.size())
			return true;
		else {
			for (Criterio originalCriterio : original.getCriterios()) {
				for (int i = 0; i < modified.getCriterios().size(); i++) {
					Criterio modifiedCriterio = modified.getCriterios().get(i);
					if (originalCriterio.getNombre().equals(
							modifiedCriterio.getNombre())
							&& !originalCriterio.getValorEsperado().equals(
									getExpectedValues()[i]))
						return true;
				}
			}
		}

		return false;
	}

	public void resetAnalisisToPasoForm() {
		getAnalisisController().setSelectedAnalisis(
				getAnalisisController().newBaseItem());
		
		getAnalisisController().setRefreshedCriterios(new ArrayList<Criterio>());
		setExpectedValues(new String[50]);

	}

	private List<Criterio> getCriterioValuesAsList() {
		List<Criterio> resultList = new ArrayList<Criterio>();
		int index = 0;
		for (Criterio entry : getAnalisisController().getRefreshedCriterios()) {
			Criterio tempCri = new Criterio(entry);
			tempCri.setValorEsperado(getExpectedValues()[index]);
			resultList.add(tempCri);
			index++;
		}
		return resultList;
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
	 * @param producto
	 *            the producto to set
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
	 * @param productController
	 *            the productController to set
	 */
	public void setProductController(ProductController productController) {
		this.productController = productController;
	}

	/**
	 * @return the expectedValues
	 */
	public String[] getExpectedValues() {
		if (this.expectedValues == null || this.expectedValues.length == 0) {
			this.expectedValues = new String[50];
		}

		return expectedValues;
	}

	/**
	 * @param expectedValues
	 *            the expectedValues to set
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
	 * @param analisisController
	 *            the analisisController to set
	 */
	public void setAnalisisController(AnalisisController analisisController) {
		this.analisisController = analisisController;
	}

	/**
	 * @return the pasos
	 */
	public List<Paso> getPasos() {
		return pasos;
	}

	/**
	 * @param pasos
	 *            the pasos to set
	 */
	public void setPasos(List<Paso> pasos) {
		this.pasos = pasos;
	}

}
