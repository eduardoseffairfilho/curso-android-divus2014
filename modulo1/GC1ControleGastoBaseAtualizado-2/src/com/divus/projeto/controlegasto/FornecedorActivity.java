package com.divus.projeto.controlegasto;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.divus.projeto.controlegasto.model.Fornecedor;
import com.divus.projeto.controlegasto.task.FornecedorListener;
import com.divus.projeto.controlegasto.task.LoadFornecedorTask;
import com.divus.projeto.controlegasto.task.SalvarFornecedorTask;

public class FornecedorActivity extends Activity implements FornecedorListener {
	
	

	private ListView lstFornecedores;
	private List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
	private ArrayAdapter<Fornecedor> adapter;
	private ProgressDialog progress;
	private EditText edtDescricao;
	private EditText edtTelefone;
	private EditText edtEndereco;
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		System.out.println("restore.. ignorando.");
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fornecedor);
		
		this.lstFornecedores = (ListView) findViewById(R.id.lstFornecedores);
		this.edtDescricao = (EditText) findViewById(R.id.edtDescricaoFornecedor);
		this.edtTelefone = (EditText) findViewById(R.id.edtTelefoneFornecedor);
		this.edtEndereco = (EditText) findViewById(R.id.edtEnderecoFornecedor);
		
		carregarFornecedor();
	}

	@Override
	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}


	@Override
	public void notifyList() {
		adapter = new ArrayAdapter<Fornecedor>(this, android.R.layout.simple_list_item_1, fornecedores);
		this.lstFornecedores.setAdapter(adapter);
		progress.dismiss();
	}
	
	public void salvarFormOnClick(View view) {
		SalvarFornecedorTask task = new SalvarFornecedorTask(this);
		progress = ProgressDialog.show(this, "Fornecedor", "Salvando...");
		
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setNome(edtDescricao.getText().toString());
		fornecedor.setFone(edtTelefone.getText().toString());
		fornecedor.setEndereco(edtEndereco.getText().toString());
		fornecedor.setId(0);
		
		task.execute(fornecedor);
	}
	
	private void carregarFornecedor() {
		LoadFornecedorTask task = new LoadFornecedorTask(this);
		progress = ProgressDialog.show(this, "Fornecedor", "Carregando");
		task.execute();
		
	}

	@Override
	public void executarSincronizacao() {
		progress.dismiss();
		carregarFornecedor();
	}

}
