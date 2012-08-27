package ar.edu.utn.frba.proyecto.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;
import org.springframework.util.StringUtils;

import ar.edu.utn.frba.proyecto.dao.ProductDao;
import ar.edu.utn.frba.proyecto.datamodel.ProductDataModel;
import ar.edu.utn.frba.proyecto.domain.Producto;

@ManagedBean
@SessionScoped
public class ProductController extends AbstractController {

	private static final long serialVersionUID = 4452567671269942318L;

	private Producto currentProduct = new Producto(0, "", "");

	private List<Producto> productos;
	private Producto selectedProduct = new Producto(0, INITIAL_NAME,
			INITIAL_DESC);
	private Producto tempSelectedProduct = new Producto(0, INITIAL_NAME,
			INITIAL_DESC);

	private List<Producto> filteredProducts;
	private Producto[] selectedProducts;
	private List<Producto> selectedProductsList;

	private ProductDataModel productDataModel;
	private ProductDataModel histProductDataModel;
	
	private ProductDao productDao = new ProductDao();
	
	private int activeIndexTab;

	/**
	 * @return the productos
	 */
	public List<Producto> getProductos() {
		if (this.productos == null)
			this.productos = new ArrayList<Producto>();

		return this.productos;
	}

	/**
	 * @return the productos
	 */
	public List<Producto> getProductosHist() {
		this.productos = new ArrayList<Producto>();
		this.productos.add(new Producto(1, "Alejandra", "DescripcionI"));
		this.productos.add(new Producto(2, "Mariano", "DescripcionJ"));
		this.productos.add(new Producto(3, "Leandro", "DescripcionK"));
		this.productos.add(new Producto(4, "Jesica", "DescripcionL"));
		this.productos.add(new Producto(5, "Mario", "DescripcionM"));
		this.productos.add(new Producto(6, "Mamita Mary", "DescripcionN"));
		this.productos.add(new Producto(7, "Maqui", "DescripcionO"));
		this.productos.add(new Producto(8, "Puki", "DescripcionP"));
		return this.productos;
	}
	
	public final void onTabChange(final TabChangeEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		TabView tabView = (TabView) event.getComponent();
		String activeIndexValue = params.get(tabView.getClientId(context) + "_tabindex");
        this.activeIndexTab = Integer.parseInt(activeIndexValue);
	}	

	public void storeOriginalProduct(Producto producto){
			tempSelectedProduct = new Producto(producto);
	}
	
	public void restoreOriginalProduct() {
		selectedProduct = new Producto(tempSelectedProduct);
	}

	public void resetCurrent() {
		currentProduct = new Producto(0, "", "");
	}
	
	public void resetSelectedProducts() {
		selectedProducts = null;
	}

	public void addProduct() {
		getProductos().add(currentProduct);
		setProductDataModel(new ProductDataModel(getProductos()));
		
		String confirmMessage = "Producto " + currentProduct.getNombre() + " creado satisfactoriamente";
        FacesContext.getCurrentInstance().addMessage("addProductGrowlMessageKeys", new FacesMessage(FacesMessage.SEVERITY_INFO,confirmMessage, null));
        resetCurrent();
    }

	public void addProductReal() {
		currentProduct = productDao.addProduct(currentProduct);
		getProductos().add(currentProduct);
		
		String confirmMessage = "Producto " + currentProduct.getNombre() + " creado satisfactoriamente";
        FacesContext.getCurrentInstance().addMessage("addProductGrowlMessageKeys", new FacesMessage(FacesMessage.SEVERITY_INFO,confirmMessage, null));
        resetCurrent();
	}

	public void updateProduct() {

		for (Producto producto : getProductos()) {
			if (producto.getProdId().equals(selectedProduct.getProdId())) {
				producto.setDescripcion(selectedProduct.getDescripcion());
				producto.setNombre(selectedProduct.getNombre());
			}
		}
		setProductDataModel(new ProductDataModel(getProductos()));
		String confirmMessage = "Producto " + selectedProduct.getNombre() + " modificado satisfactoriamente";
        FacesContext.getCurrentInstance().addMessage("updateProductGrowlMessageKeys", new FacesMessage(FacesMessage.SEVERITY_INFO,confirmMessage, null));

	}

	public void deleteProducts() {
		getProductos().removeAll(Arrays.asList(getSelectedProducts()));
		setProductDataModel(new ProductDataModel(getProductos()));
	}

	public String deletedProductIds(String splitter) {

		if (getSelectedProducts() != null && getSelectedProducts().length > 0) {
			List<Integer> productIds = new ArrayList<Integer>();
			for (Producto producto : getSelectedProducts())
				productIds.add(producto.getProdId());
			return StringUtils
					.collectionToDelimitedString(productIds, splitter);
		}

		return "";
	}

	/**
	 * @param productos
	 *            the productos to set
	 */
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
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

	/**
	 * @return the productDataModel
	 */
	public ProductDataModel getProductDataModel() {
		if (productDataModel == null) {
			productDataModel = new ProductDataModel(getProductos());
		}
		return productDataModel;
	}

	/**
	 * @param productDataModel
	 *            the productDataModel to set
	 */
	public void setProductDataModel(ProductDataModel productDataModel) {
		this.productDataModel = productDataModel;
	}

	/**
	 * @return the histProductDataModel
	 */
	public ProductDataModel getHistProductDataModel() {
		if (histProductDataModel == null) {
			histProductDataModel = new ProductDataModel(getProductosHist());
		}
		return histProductDataModel;
	}

	/**
	 * @param histProductDataModel
	 *            the histProductDataModel to set
	 */
	public void setHistProductDataModel(ProductDataModel histProductDataModel) {
		this.histProductDataModel = histProductDataModel;
	}

	/**
	 * @return currentProduct
	 */
	public Producto getCurrentProduct() {
		return currentProduct;
	}

	/**
	 * @param currentProduct
	 *            the currentProduct
	 */
	public void setCurrentProduct(Producto currentProduct) {
		this.currentProduct = currentProduct;
	}

	/**
	 * @return the selectedProducts
	 */
	public Producto[] getSelectedProducts() {
		return selectedProducts;
	}

	/**
	 * @param selectedProducts
	 *            the selectedProducts to set
	 */
	public void setSelectedProducts(Producto[] selectedProducts) {
		this.selectedProducts = selectedProducts;
	}

	/**
	 * @return the tempSelectedProduct
	 */
	public Producto getTempSelectedProduct() {
		return tempSelectedProduct;
	}

	/**
	 * @param tempSelectedProduct
	 *            the tempSelectedProduct to set
	 */
	public void setTempSelectedProduct(Producto tempSelectedProduct) {
		this.tempSelectedProduct = tempSelectedProduct;
	}

	/**
	 * @return the selectedProductsList
	 */
	public List<Producto> getSelectedProductsList() {
		
		if (getSelectedProducts() != null && getSelectedProducts().length > 0) {
			this.selectedProductsList = Arrays.asList(getSelectedProducts());
		}
		return selectedProductsList;
	}

	/**
	 * @param selectedProductsList the selectedProductsList to set
	 */
	public void setSelectedProductsList(List<Producto> selectedProductsList) {
		this.selectedProductsList = selectedProductsList;
	}

	/**
	 * @return the filteredProducts
	 */
	public List<Producto> getFilteredProducts() {
		return filteredProducts;
	}

	/**
	 * @param filteredProducts the filteredProducts to set
	 */
	public void setFilteredProducts(List<Producto> filteredProducts) {
		this.filteredProducts = filteredProducts;
	}

	/**
	 * @return the activeIndexTab
	 */
	public int getActiveIndexTab() {
		return activeIndexTab;
	}

	/**
	 * @param activeIndexTab the activeIndexTab to set
	 */
	public void setActiveIndexTab(int activeIndexTab) {
		this.activeIndexTab = activeIndexTab;
	}

}
