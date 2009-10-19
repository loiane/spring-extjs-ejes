package com.loiane.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.loiane.model.Contato;
import com.loiane.model.Dados;
import com.loiane.service.DadoService;

/**
 * Controller - Spring
 * 
 * @author Loiane Groner
 */
public class ContatoController extends MultiActionController  {

	private DadoService dadoService;

	public ModelAndView setColunas(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String iniParam = request.getParameter("colunas");
		String[] colunas = iniParam.substring(13).split("%2C");
		
		Dados dados = (Dados) request.getSession().getAttribute("dados");
		dados.setColunas(colunas);
		dados.setColumnModel(dadoService.geraColumnModel(colunas));
		dados.setStoreFiels(dadoService.geraCamposStoreField(colunas));
		
		return new ModelAndView();
	}
	
	public ModelAndView getStoreFieldJson(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		//obtém o parâmetro de início
		String iniParam = request.getParameter("start");
		int inicio = (iniParam == null) ? 0 : Integer.parseInt(iniParam);

		Dados dados = (Dados) request.getSession().getAttribute("dados");
		ArrayList<Contato> contatos = (ArrayList<Contato>) request.getSession().getAttribute("contatos");
		
		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("total", DadoService.TOTAL_REGISTROS);
		modelMap.put("rows", dadoService.geraDadosResultado(inicio,dados.getColunas(),
				contatos));
		modelMap.put("metaData", dadoService.geraMetaDados(dados.getColunas()));

		return new ModelAndView("jsonView", modelMap);

	}

	public ModelAndView getColumnsJson(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Dados dados = (Dados) request.getSession().getAttribute("dados");
		
		Map<String,Object> modelMap = new HashMap<String,Object>(2);
		modelMap.put("rows", dadoService.geraColumnModel(dados.getColunas()));

		return new ModelAndView("jsonView", modelMap);

	}

	/**
	 * Para uso do Spring - DI
	 * @param dadoService
	 */
	public void setDadoService(DadoService dadoService) {
		this.dadoService = dadoService;
	}

}
