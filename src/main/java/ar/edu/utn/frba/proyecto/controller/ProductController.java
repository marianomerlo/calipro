package ar.edu.utn.frba.proyecto.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.util.StringUtils;

import ar.edu.utn.frba.proyecto.datamodel.ProductDataModel;
import ar.edu.utn.frba.proyecto.domain.Producto;

@ManagedBean
@SessionScoped
public class ProductController extends AbstractController {

	private static final long serialVersionUID = 4452567671269942318L;

	private String fakeId = String.valueOf(Math.random());
	private Producto currentProduct = new Producto(fakeId, "", "");

	private String INITIAL_NAME = "initialName";
	private String INITIAL_DESC = "initialDesc";
	private List<Producto> productos;
	private Producto selectedProduct = new Producto(fakeId, INITIAL_NAME,
			INITIAL_DESC);
	private Producto tempSelectedProduct = new Producto(fakeId, INITIAL_NAME,
			INITIAL_DESC);

	private Producto[] selectedProducts;
	private List<Producto> selectedProductsList;

	private ProductDataModel productDataModel;
	private ProductDataModel histProductDataModel;

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
		this.productos.add(new Producto("I", "Alejandra", "DescripcionI"));
		this.productos.add(new Producto("J", "Mariano", "DescripcionJ"));
		this.productos.add(new Producto("K", "Leandro", "DescripcionK"));
		this.productos.add(new Producto("L", "Jesica", "DescripcionL"));
		this.productos.add(new Producto("M", "Mario", "DescripcionM"));
		this.productos.add(new Producto("N", "Mamita Mary", "DescripcionN"));
		this.productos.add(new Producto("O", "Maqui", "DescripcionO"));
		this.productos.add(new Producto("P", "Puki", "DescripcionP"));
		return this.productos;
	}

	public void storeOriginalProduct(Producto producto){
			tempSelectedProduct = new Producto(producto);
	}
	
	public void restoreOriginalProduct() {
		selectedProduct = new Producto(tempSelectedProduct);
	}

	public void resetCurrent() {
		currentProduct = new Producto(currentProduct.getProdId() + "AA", "", "");
	}
	
	public void resetSelectedProducts() {
		selectedProducts = null;
	}

	public void addProduct() {
		getProductos().add(currentProduct);
		setProductDataModel(new ProductDataModel(getProductos()));
	}

	public void updateProduct() {

		for (Producto producto : getProductos()) {
			if (producto.getProdId().equals(selectedProduct.getProdId())) {
				producto.setDescripcion(selectedProduct.getDescripcion());
				producto.setNombre(selectedProduct.getNombre());
			}
		}
		setProductDataModel(new ProductDataModel(getProductos()));
	}

	public void deleteProducts() {
		getProductos().removeAll(Arrays.asList(getSelectedProducts()));
		setProductDataModel(new ProductDataModel(getProductos()));
	}

	public String deletedProductIds(String splitter) {

		if (getSelectedProducts() != null && getSelectedProducts().length > 0) {
			List<String> productIds = new ArrayList<String>();
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

}
