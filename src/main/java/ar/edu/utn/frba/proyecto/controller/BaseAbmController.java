package ar.edu.utn.frba.proyecto.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.model.SelectableDataModel;
import org.springframework.util.StringUtils;

import ar.edu.utn.frba.proyecto.dao.AbmDao;
import ar.edu.utn.frba.proyecto.domain.BaseObject;

public abstract class BaseAbmController<T extends BaseObject> extends BaseController<T> implements Serializable {

	protected String addMessageKey;
	protected String updateMessageKey;
	
	/* Spring Properties */
	
	protected T currentItem = newBaseItem();
	protected T selectedItem = newBaseItem();
	protected T originalSelectedItem = newBaseItem();
	
	protected T[] selectedItems;

	protected SelectableDataModel<T> dataModel; 
	
	protected abstract AbmDao<T> getDao(); 
	
	protected abstract T newBaseItem();

	protected abstract T newBaseItem(T item);
	
	protected abstract SelectableDataModel<T> newDataModel(List<T> all);
	
	protected abstract boolean isDifferent();
	
	
	
	public void resetCurrent() {
		this.currentItem = newBaseItem();
		extraResetCurrentProcess();
	}
	
	protected void extraResetCurrentProcess() {
		// By default, do nothing
		
	}

	public void refreshItems() {
		this.items = getDao().getAll();
		extraRefreshItemsProcess();
		this.dataModel = newDataModel(this.items);
	}
	
	protected void extraRefreshItemsProcess() {
		// By default, do nothing
	}

	public void addItem() {
		getDao().add(currentItem);
		extraAddItemProcess();

		resetCurrent();
		refreshItems();
		
	}

	protected void extraAddItemProcess() {
		String confirmMessage = ITEM_NAME + " " + currentItem.getIdentifingName() + " creado satisfactoriamente";
		FacesContext.getCurrentInstance().addMessage(getAddMessageKey(),
				new FacesMessage(FacesMessage.SEVERITY_INFO, confirmMessage,
						null));
	}

	public void updateItem(){
		if ( isDifferent() ){
			getDao().update(selectedItem);

			extraUpdateItemProcess();
			
			storeOriginalItem(selectedItem);
			refreshItems();
		} else {
			String errorMessage = "No ha realizado modificaciones";
			FacesContext.getCurrentInstance().addMessage(getUpdateMessageKey(),
					new FacesMessage(FacesMessage.SEVERITY_WARN, errorMessage,null));
		}
	}
	
	protected void extraUpdateItemProcess() {
		String confirmMessage = ITEM_NAME + " " + selectedItem.getIdentifingName() + " modificado satisfactoriamente";
		FacesContext.getCurrentInstance().addMessage(getUpdateMessageKey(), new FacesMessage(FacesMessage.SEVERITY_INFO,confirmMessage, null));
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
	
	public T[] getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(T[] selectedItems) {
		this.selectedItems = selectedItems;
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

	public SelectableDataModel<T> getDataModel() {
		if ( this.dataModel == null){
			this.dataModel = newDataModel(getItems());
		}
		return dataModel;
	}

	public void setDataModel(SelectableDataModel<T> dataModel) {
		this.dataModel = dataModel;
	}

	public String getAddMessageKey() {
		if ( this.addMessageKey == null)
			this.addMessageKey = "add" + ITEM_NAME + "GrowlMessageKeys";
		
		return addMessageKey;
	}

	public void setAddMessageKey(String addMessageKey) {
		this.addMessageKey = addMessageKey;
	}

	public String getUpdateMessageKey() {
		if (this.updateMessageKey == null)
			 this.updateMessageKey = "update" + ITEM_NAME + "GrowlMessageKeys";
		
		return updateMessageKey;
	}

	public void setUpdateMessageKey(String updateMessageKey) {
		this.updateMessageKey = updateMessageKey;
	}
	
	
}
