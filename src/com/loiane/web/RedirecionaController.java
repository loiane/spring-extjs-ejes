package com.loiane.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.loiane.model.Contato;
import com.loiane.model.Dados;
import com.loiane.service.ExcelService;

public class RedirecionaController extends MultiActionController {

	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ArrayList<Contato> contatos = new ExcelService().populaContatos();
		
		request.getSession().setAttribute("contatos", contatos);
		request.getSession().setAttribute("dados", new Dados());
		
		return new ModelAndView("/griddinamico/colunas");
	}
	
	public ModelAndView colunas(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		return new ModelAndView("/griddinamico/colunas");
	}

	public ModelAndView gridDinamico(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		return new ModelAndView("/griddinamico/griddinamico");
	}
	
	public ModelAndView index(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		return new ModelAndView("index");
	}
}
