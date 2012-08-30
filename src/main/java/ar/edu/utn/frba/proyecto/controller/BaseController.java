package ar.edu.utn.frba.proyecto.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.SelectableDataModel;
import org.springframework.util.StringUtils;

import ar.edu.utn.frba.proyecto.dao.Dao;
import ar.edu.utn.frba.proyecto.domain.AuditObject;

public abstract class BaseController<T extends AuditObject> implements Serializable {

	private static final long serialVersionUID = 3567648080222548124L;
	
	/* Spring Properties */
	
	private String ITEM_NAME;
	
	private List<T> items;
	private T[] selectedItems;
	private T currentItem = newBaseItem();
	private T selectedItem = newBaseItem();
	private T originalSelectedItem = newBaseItem();
	
	private SelectableDataModel<T> dataModel; 
	
	private int activeIndexTab;
	
	protected abstract Dao<T> getDao(); 
	
	protected abstract T newBaseItem();

	protected abstract T newBaseItem(T item);
	
	protected abstract SelectableDataModel<T> newDataModel(List<T> all);
	
	protected abstract boolean isDifferent();
	
	public List<T> getItems() {
		if (this.items == null)
			this.items = getDao().getAll();

		return this.items;
	}
	
	public void resetCurrent() {
		this.currentItem = newBaseItem();
	}
	
	public void refreshItems() {
		this.items = getDao().getAll();
		this.dataModel = newDataModel(this.items);
	}
	
	public void addItem() {
		getDao().add(currentItem);

		String confirmMessage = ITEM_NAME + " " + currentItem.getIdentifingName()
				+ " creado satisfactoriamente";
		FacesContext.getCurrentInstance().addMessage(
				"add" + ITEM_NAME + "GrowlMessageKeys",
				new FacesMessage(FacesMessage.SEVERITY_INFO, confirmMessage,
						null));
		resetCurrent();
		refreshItems();
	}

	public void updateItem(){
		String messageKey = "update" + ITEM_NAME + "GrowlMessageKeys";
		if ( isDifferent() ){
			getDao().update(selectedItem);

			String confirmMessage = ITEM_NAME + " " + selectedItem.getIdentifingName() + " modificado satisfactoriamente";
			FacesContext.getCurrentInstance().addMessage(messageKey, new FacesMessage(FacesMessage.SEVERITY_INFO,confirmMessage, null));
			
			storeOriginalItem(selectedItem);
			refreshItems();
		} else {
			String errorMessage = "No ha realizado modificaciones";
			FacesContext.getCurrentInstance().addMessage(messageKey,
					new FacesMessage(FacesMessage.SEVERITY_WARN, errorMessage,null));
		}
	}
	
	public void deleteItems(){
		getDao().delete(Arrays.asList(getSelectedItems()));
		refreshItems();
	}
	
	public void storeOriginalItem(T item) {
		originalSelectedItem = newBaseItem(item);
	}
	
	public void restoreOriginalItem() {
		selectedItem = newBaseItem(originalSelectedItem);
	}
	
	public void resetSelectedItems() {
		selectedItems = null;
	}
	
	public String deletedItemIds(String splitter) {

		if (getSelectedItems() != null && getSelectedItems().length > 0) {
			List<Integer> itemIds = new ArrayList<Integer>();
			for (T item : getSelectedItems())
				itemIds.add(item.getId());
			return StringUtils
					.collectionToDelimitedString(itemIds, splitter);
		}

		return "";
	}

	public final void onTabChange(final TabChangeEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> params = context.getExternalContext()
				.getRequestParameterMap();
		TabView tabView = (TabView) event.getComponent();
		String activeIndexValue = params.get(tabView.getClientId(context)
				+ "_tabindex");
		this.setActiveIndexTab(Integer.parseInt(activeIndexValue));
	}

	public String getITEM_NAME() {
		return ITEM_NAME;
	}

	public void setITEM_NAME(String iTEM_NAME) {
		ITEM_NAME = iTEM_NAME;
	}

	public T[] getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(T[] selectedItems) {
		this.selectedItems = selectedItems;
	}

	public T getCurrentItem() {
		return currentItem;
	}

	public void setCurrentItem(T currentItem) {
		this.currentItem = currentItem;
	}

	public T getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(T selectedItem) {
		this.selectedItem = selectedItem;
	}

	public T getOriginalSelectedItem() {
		return originalSelectedItem;
	}

	public void setOriginalSelectedItem(T originalSelectedItem) {
		this.originalSelectedItem = originalSelectedItem;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public int getActiveIndexTab() {
		return activeIndexTab;
	}

	public void setActiveIndexTab(int activeIndexTab) {
		this.activeIndexTab = activeIndexTab;
	}

	public SelectableDataModel<T> getDataModel() {
		if ( this.dataModel == null){
			this.dataModel = newDataModel(getItems());
		}
		return dataModel;
	}

	public void setDataModel(SelectableDataModel<T> dataModel) {
		this.dataModel = dataModel;
	}
	
	
}
