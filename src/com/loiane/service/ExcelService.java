package com.loiane.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.loiane.model.Contato;

/**
 * Classe que faz a leitura dos dados de um arquivo excel
 * 
 * @author Loiane Groner
 *
 */
public class ExcelService {

	/**
	 * Popula um array de Contatos com os dados
	 * encontrados no arquivo excel
	 * @return lista de contatosO
	 * @throws IOException
	 */
	public ArrayList<Contato> populaContatos() throws IOException
	{
		//modificar para o path de sua máquina local
		InputStream inp = new FileInputStream("D:\\workspaceEjes\\spring-extjs-ejes\\excel.xls");
		HSSFWorkbook wb = new HSSFWorkbook(inp);

		HSSFRow row;
		
		ArrayList<Contato> contatos = new ArrayList<Contato>();
		Contato contato;

		HSSFSheet sheet1 = wb.getSheetAt(0);
		for (int i=1; i<99; i++){
			row = sheet1.getRow(i);
			
			contato = new Contato();
			contato.setNome(row.getCell(0).getRichStringCellValue().getString());
			contato.setEmail(row.getCell(1).getRichStringCellValue().getString());
			contato.setPais(row.getCell(2).getRichStringCellValue().getString());

			contatos.add(contato);
		}

		return contatos;
	}
}
