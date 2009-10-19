package com.loiane.model;

import java.io.Serializable;

/**
 * Classe POJO Contato - populado pelo Excel
 * 
 * @author Loiane Groner
 *
 */
public class Contato implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String email;
	private String pais;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}

	
}
