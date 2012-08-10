package ar.edu.utn.frba.proyecto.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import ar.edu.utn.frba.proyecto.datamodel.ProductDataModel;
import ar.edu.utn.frba.proyecto.domain.Producto;


/**
 * 
 * @author mariano.gonzalez@mulesoft.com
 *
 */
@ManagedBean
@SessionScoped
public class ProductController extends AbstractController {

	private static final long serialVersionUID = 4452567671269942318L;
	
	private Producto currentProduct = new Producto(String.valueOf(Math.random()), "", "");
	
	private List<Producto> productos;
	private Producto selectedProduct;
	
	private ProductDataModel productDataModel;
	private ProductDataModel histProductDataModel;
	
	
	/**
	 * @return the productos
	 */
	public List<Producto> getProductos() {
		if ( this.productos == null)
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
	
	public void addProduct(){
		getProductos().add(currentProduct);
		setProductDataModel(new ProductDataModel(getProductos()));
		currentProduct = new Producto(currentProduct.getProdId() + "AA", "", "");
	}

	public void updateProduct(){
		
		for ( Producto producto : getProductos()){
			if ( producto.getProdId().equals(selectedProduct.getProdId())){
				producto.setDescripcion(selectedProduct.getDescripcion());
				producto.setNombre(selectedProduct.getNombre());
			}
		}
		setProductDataModel(new ProductDataModel(getProductos()));
	}
	
	/**
	 * @param productos the productos to set
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
	 * @param selectedProduct the selectedProduct to set
	 */
	public void setSelectedProduct(Producto selectedProduct) {
		this.selectedProduct = selectedProduct;
	}
	
	public void onRowSelect(SelectEvent event) {
		Producto selected = ((Producto) event.getObject());
        FacesMessage msg = new FacesMessage("Producto Elegido", selected.getNombre());

        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        this.selectedProduct = selected;
        
    }

    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Producto Unselected", ((Producto) event.getObject()).getNombre());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowDblselect(SelectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) context.getApplication().getNavigationHandler();
        Flash flash = context.getExternalContext().getFlash();
        flash.put("selectedProduct", (Producto) event.getObject());
        
        handler.performNavigation("carDetail");
    }

	/**
	 * @return the productDataModel
	 */
	public ProductDataModel getProductDataModel() {
		if ( productDataModel == null){
			productDataModel = new ProductDataModel(getProductos());
		}
		return productDataModel;
	}

	/**
	 * @param productDataModel the productDataModel to set
	 */
	public void setProductDataModel(ProductDataModel productDataModel) {
		this.productDataModel = productDataModel;
	}

	/**
	 * @return the histProductDataModel
	 */
	public ProductDataModel getHistProductDataModel() {
		if ( histProductDataModel == null){
			histProductDataModel = new ProductDataModel(getProductosHist());
		}
		return histProductDataModel;
	}

	/**
	 * @param histProductDataModel the histProductDataModel to set
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
	 * @param currentProduct the currentProduct
	 */
	public void setCurrentProduct(Producto currentProduct) {
		this.currentProduct = currentProduct;
	}

	
}
