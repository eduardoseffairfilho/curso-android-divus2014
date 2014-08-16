package com.divus.exemplo.json;

import java.io.Serializable;

public class Contato implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8142349038165801786L;
	
	private String nome;
	private String sobrenome;
	private String idade;
	private String site;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getIdade() {
		return idade;
	}
	public void setIdade(String idade) {
		this.idade = idade;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	
	@Override
	public String toString() {
		return "Nome: " + nome + ", "
				+ "\tSobrenome: " + sobrenome + "."
				+ "\n\tIdade: " + idade
				+ "\n\tSite: " + site;
	}

}
