package ar.edu.utn.frba.proyecto.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import ar.edu.utn.frba.proyecto.dao.Dao;
import ar.edu.utn.frba.proyecto.dao.ProductDao;
import ar.edu.utn.frba.proyecto.domain.Producto;

@ManagedBean
@SessionScoped
public class ProductController extends BaseController<Producto> {

	private static final long serialVersionUID = 4452567671269942318L;

	@ManagedProperty("#{productDao}")
	private ProductDao productDao;

	@Override
	protected Dao<Producto> getDao() {
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
}
