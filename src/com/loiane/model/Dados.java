package com.loiane.model;

import java.util.ArrayList;

/**
 * Representa dos dados de configuração do grid
 * 
 * @author Loiane Groner
 *
 */
public class Dados {
	
	private String[] colunas;
	private ArrayList<ColumnModel> columnModel;
	private ArrayList<StoreField> storeFiels;
	
	public String[] getColunas() {
		return colunas;
	}
	public void setColunas(String[] colunas) {
		this.colunas = colunas;
	}
	public ArrayList<ColumnModel> getColumnModel() {
		return columnModel;
	}
	public void setColumnModel(ArrayList<ColumnModel> columnModel) {
		this.columnModel = columnModel;
	}
	public ArrayList<StoreField> getStoreFiels() {
		return storeFiels;
	}
	public void setStoreFiels(ArrayList<StoreField> storeFiels) {
		this.storeFiels = storeFiels;
	}
	
}
