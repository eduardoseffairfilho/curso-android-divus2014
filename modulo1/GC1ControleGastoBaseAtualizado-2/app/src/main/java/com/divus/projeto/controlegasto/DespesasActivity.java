package com.divus.projeto.controlegasto;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.divus.projeto.controlegasto.adapter.DespesaAdapter;
import com.divus.projeto.controlegasto.dao.DespesaDAO;
import com.divus.projeto.controlegasto.model.Despesa;

public class DespesasActivity extends Activity implements OnItemClickListener {

	public final static String KEY_DESPESA = "despesa";
	
	private final static int MODO_INSERIR = 0;
	
	private final static int MODO_EDICAO = 1; 
	
	private List<Despesa> despesas = new ArrayList<Despesa>();
	
	private ListView lstDespesas;
	
	private DespesaAdapter adpater;
	
	private DespesaDAO despesaDao;
	
	private Despesa despSelecionado;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_despesas);
		
		despesaDao = DespesaDAO.getInstance(this);
		
		despesas = despesaDao.listarTodos();
		
		lstDespesas = (ListView) findViewById(R.id.lstDespesas);
		
		adpater = new DespesaAdapter(this, despesas);
		
		lstDespesas.setAdapter(adpater);
		lstDespesas.setOnItemClickListener(this);
		
	}

	public void deletarDespesaOnclick(View view){
		if (despSelecionado != null) {
			despesaDao.deletar(despSelecionado);
		} else {
			Toast.makeText(this, "Selecione um registro!", Toast.LENGTH_LONG).show();
		}
		despesas = despesaDao.listarTodos();
		adpater = new DespesaAdapter(this, despesas);
		
		lstDespesas.setAdapter(adpater);
	}
	
	public void editarDespesaOnclick(View view) {
		if (despSelecionado != null) {
			Intent intent = new Intent(this, CadastroDespesaActivity.class);
			intent.putExtra(KEY_DESPESA, despSelecionado);
			startActivityForResult(intent, MODO_EDICAO);			
		} else {
			Toast.makeText(this, "Selecione um registro!", Toast.LENGTH_LONG).show();
		}
	}
	
	public void criarDespesasOnclick(View view) {
		Intent intent = new Intent(this, CadastroDespesaActivity.class);
		startActivityForResult(intent, MODO_INSERIR);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == MODO_INSERIR && resultCode == Activity.RESULT_OK){
			Despesa desp = (Despesa) data.getSerializableExtra(KEY_DESPESA);
			despesas.add(desp);
			adpater.notifyDataSetChanged();
		} else if (requestCode == MODO_EDICAO && resultCode == Activity.RESULT_OK) {
			despesas = despesaDao.listarTodos();
			adpater = new DespesaAdapter(this, despesas);
			lstDespesas.setAdapter(adpater);
		} 
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (despSelecionado != null) {
			despSelecionado.setSelecionado(false);
		}
		despSelecionado = despesas.get(position);
		despSelecionado.setSelecionado(true);
		adpater.notifyDataSetChanged();
	}
	
}
