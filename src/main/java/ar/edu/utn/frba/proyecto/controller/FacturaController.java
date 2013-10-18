package ar.edu.utn.frba.proyecto.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.SelectableDataModel;

import ar.edu.utn.frba.proyecto.dao.impl.FacturaDao;
import ar.edu.utn.frba.proyecto.domain.Factura;

@ManagedBean
@SessionScoped
public class FacturaController extends BaseAbmController<Factura> {

	@ManagedProperty("#{facturaDao}")
	private FacturaDao facturaDao;
	
	@Override
	protected FacturaDao getDao() {
		return this.facturaDao;
	}
	
	public void setFacturaDao(FacturaDao facturaDao) {
		this.facturaDao = facturaDao;
	}

	@Override
	protected Factura newBaseItem() {
		return new Factura();
	}

	@Override
	protected Factura newBaseItem(Factura item) {
		return new Factura(item);
	}

	@Override
	protected boolean isDifferent() {
		return false;
	}

	@Override
	protected SelectableDataModel<Factura> newDataModel(List<Factura> all) {
		return null;
	}
	
	public Integer getLastFactura(){
		return getDao().getLastFactura();
	}

}
