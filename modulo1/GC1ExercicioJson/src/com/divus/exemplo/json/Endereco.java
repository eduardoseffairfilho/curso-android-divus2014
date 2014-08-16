package com.divus.exemplo.json;

import java.io.Serializable;

public class Endereco implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3467904046510063867L;
	
	private String rua;
	private String numero;
	private String telefone;
	private String responsavel;
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	
	@Override
	public String toString() {
		return "Endereço: Rua " + rua + ", número " + numero + ". Telefone " + telefone + ". Responsável: " + responsavel;
	}
}
