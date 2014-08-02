package com.divus.academia.android.GC2AppBoaViagem.model;

import java.io.Serializable;

public class Despesa implements Serializable{
	
	private static final long serialVersionUID = -3258181198025939021L;
	
	private Long id;
	private String descricao;
	private String categoria;
	private double valor;
	private String data;
	private String local;
	
	public Despesa() {
	}
	
	public Despesa(String descricao, String categoria, double valor, String data, String local) {
		super();
		this.descricao = descricao;
		this.categoria = categoria;
		this.valor = valor;
		this.data = data;
		this.local = local;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	/*@Override
	public String toString() {
		return "Despesa [descricao=" + descricao + ", categoria=" + categoria
				+ ", valor=" + valor + ", data=" + data + ", local=" + local
				+ "]";
	}*/

	@Override
	public String toString() {
		return getDescricao();
	}
}
