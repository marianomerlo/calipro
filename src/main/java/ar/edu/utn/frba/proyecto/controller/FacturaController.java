package ar.edu.utn.frba.proyecto.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.StreamedContent;

import ar.edu.utn.frba.proyecto.dao.impl.FacturaDao;
import ar.edu.utn.frba.proyecto.domain.Factura;

@ManagedBean
@SessionScoped
public class FacturaController extends BaseAbmController<Factura> {

	@ManagedProperty("#{facturaDao}")
	private FacturaDao facturaDao;
	
	private StreamedContent barcode;
	
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

	/**
	 * @return the barcode
	 */
	public StreamedContent getBarcode() {
		return barcode;
	}

	/**
	 * @param barcode the barcode to set
	 */
	public void setBarcode(StreamedContent barcode) {
		this.barcode = barcode;
	}

	public void generateBarcode() throws OutputException, BarcodeException, FileNotFoundException {
		File barcodeFile = new File("dynamicbarcode");  
        BarcodeImageHandler.saveJPEG(BarcodeFactory.createCodabar(String.format("%08d", getCurrentItem().getId())), barcodeFile);  
        barcode = new DefaultStreamedContent(new FileInputStream(barcodeFile), "image/jpeg");
	}

}
