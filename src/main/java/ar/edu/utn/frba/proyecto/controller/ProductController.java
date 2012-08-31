package ar.edu.utn.frba.proyecto.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.SelectableDataModel;

import ar.edu.utn.frba.proyecto.dao.AbmDao;
import ar.edu.utn.frba.proyecto.dao.impl.ProductDao;
import ar.edu.utn.frba.proyecto.datamodel.ProductDataModel;
import ar.edu.utn.frba.proyecto.domain.Producto;

@ManagedBean
@SessionScoped
public class ProductController extends BaseAbmController<Producto> {

	@ManagedProperty("#{productDao}")
	private ProductDao productDao;

	@Override
	protected AbmDao<Producto> getDao() {
		return this.productDao;
	}

	@Override
	protected Producto newBaseItem() {
		return new Producto(0, "", "");
	}

	@Override
	protected Producto newBaseItem(Producto item) {
		return new Producto(item.getId(), item.getNombre(), item.getDescripcion());
	}

	@Override
	protected boolean isDifferent() {
		return !getOriginalSelectedItem().getNombre().equals(getSelectedItem().getNombre()) || 
				!getOriginalSelectedItem().getDescripcion().equals(getSelectedItem().getDescripcion());
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	protected SelectableDataModel<Producto> newDataModel(List<Producto> all) {
		return new ProductDataModel(all);
	}
}
