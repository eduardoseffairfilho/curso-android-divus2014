package com.divus.projeto.controlegasto.task;

import java.util.List;

import com.divus.projeto.controlegasto.model.Fornecedor;

public interface FornecedorListener {
	
	void setFornecedores(List<Fornecedor> listaFornecedores);

	void notifyList();
}
