package com.divus.projeto.controlegasto.model;

import java.io.Serializable;

public class Despesa implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String descricao;
	
	private String categoria;
	
	private double valor;
	
	private String data;
	
	private String local;
	
	private boolean selecionado = false; 

	public Despesa() {
	}
	
	public Despesa(Integer id, String descricao, String categoria, double valor, 
			String data, String local) {
		this.id = id;
		this.descricao = descricao;
		this.categoria = categoria;
		this.valor = valor;
		this.data = data;
		this.local = local;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

	@Override
	public String toString() {
		return descricao;
	}
	
}
