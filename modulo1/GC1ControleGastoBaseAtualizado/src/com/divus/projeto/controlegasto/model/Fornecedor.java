package com.divus.projeto.controlegasto.model;

import java.io.Serializable;

public class Fornecedor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8579809874236056677L;
	
	private Integer id;
	private String nome;
	private String endereco;
	private String fone;
	
	public Fornecedor() {
	}

	public Fornecedor(Integer id, String nome, String endereco, String fone) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.fone = fone;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	
	@Override
	public String toString() {
		return "Fornecedor [id=" + id + ", nome=" + nome + ", endereco="
				+ endereco + ", fone=" + fone + "]";
	}
}
