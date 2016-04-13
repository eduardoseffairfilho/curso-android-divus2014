package com.divus.projeto.controlegasto;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.divus.projeto.controlegasto.model.Fornecedor;
import com.divus.projeto.controlegasto.task.FornecedorListener;
import com.divus.projeto.controlegasto.task.LoadFornecedorTask;

public class FornecedorActivity extends Activity implements FornecedorListener {
	
	private ListView lstFornecedores;
	private List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
	private ArrayAdapter<Fornecedor> adapter;
	private ProgressDialog progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fornecedor);
		
		this.lstFornecedores = (ListView) findViewById(R.id.lstFornecedores);
		
		LoadFornecedorTask task = new LoadFornecedorTask(this);
		progress = ProgressDialog.show(this, "Fornecedor", "Carregando...");
		task.execute();
	}

	@Override
	public void setFornecedores(List<Fornecedor> listaFornecedores) {
		this.fornecedores = listaFornecedores;
		adapter.notifyDataSetChanged();
	}

	@Override
	public void notifyList() {
		adapter = new ArrayAdapter<Fornecedor>(this, android.R.layout.simple_list_item_1, fornecedores);
		this.lstFornecedores.setAdapter(adapter);
		progress.dismiss();
	}

}
