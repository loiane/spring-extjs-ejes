package com.loiane.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.loiane.model.ColumnModel;
import com.loiane.model.Contato;
import com.loiane.model.StoreField;

/**
 * Gera dados aleatórios para preencher o grid dinamicamente
 * 
 * @author Loiane Groner
 */
public class DadoService {
	
	//limite de dados por página
	public static int LIMITE = 10;
	
	//número de registros a serem gerados
	public static int TOTAL_REGISTROS = 100;
	
	/**
	 * Gera as colunas que serão usadas na configuração dinâmica
	 * do grid
	 * @return lista de ColumnModel
	 */
	public ArrayList<ColumnModel> geraColumnModel(String[] colSelectionadas){
		
		ArrayList<ColumnModel> colunas = new ArrayList<ColumnModel>();
		
		for (int i=0 ;i<colSelectionadas.length; i++){
			ColumnModel column = new ColumnModel();
			
			column.setDataIndex(colSelectionadas[i]);
			column.setHeader(colSelectionadas[i].toUpperCase());
			column.setAlign("left");
			column.setSortable("true");
			
			if (colSelectionadas[i].equals("Nome")){
				column.setWidth(200);
			} else if (colSelectionadas[i].equals("Email")){
				column.setWidth(400);
			} else if (colSelectionadas[i].equals("Endereco")){
				column.setWidth(200);
			}
			
			colunas.add(column);
		}
		
		return colunas;
	}
	
	/**
	 * Gera os campos que serão usados para configurar dinamicamente
	 * o data Store
	 * @return lista de StoreField
	 */
	public ArrayList<StoreField> geraCamposStoreField(String[] colSelecionadas){
		
		ArrayList<StoreField> campos = new ArrayList<StoreField>();
		
		for (int i=0 ;i<colSelecionadas.length; i++){
			StoreField field = new StoreField();
			
			//precisa ser o mesmo que o dataIndex do ColumnModel
			field.setName(colSelecionadas[i]); 
			
			campos.add(field);
		}
		
		return campos;
	}
	
	
	/**
	 * Gera os resultados que irão preencher o grid
	 * @return lista de Resultados
	 */
	public List<HashMap<String, String>> geraDadosResultado(int inicio, String[] colunas,
			ArrayList<Contato> contatos){
		
		ArrayList<HashMap<String, String>> dados;
		dados = new ArrayList<HashMap<String,String>>(LIMITE);
		
		for (int j=inicio; j<(inicio+LIMITE) && (j<TOTAL_REGISTROS); j++){
			HashMap<String, String> dado = new HashMap<String, String>(LIMITE);
			
			for (int i=0 ;i<colunas.length; i++){

				//a chave deve ser a mesma do dataIndex do ColumnModel
				
				if (colunas[i].equals("Nome")){
					dado.put((colunas[i]), contatos.get(j).getNome());
				} else if (colunas[i].equals("Email")){
					dado.put((colunas[i]), contatos.get(j).getEmail());
				} else if (colunas[i].equals("Endereco")){
					dado.put((colunas[i]), contatos.get(j).getPais());
				}
			}
			dados.add(dado);
		}
		
		return dados;
	}
	
	/**
	 * Gera os metadados para configuração dinâmica do data Store
	 * @return mapa com os metadados
	 */
	public Map<String,Object> geraMetaDados(String[] colunas){
		Map<String,Object> metaDados = new HashMap<String,Object>(3);
		metaDados.put("root", "rows");
		metaDados.put("totalProperty", "total");
		metaDados.put("fields", geraCamposStoreField(colunas));
		
		return metaDados;
	}
}
