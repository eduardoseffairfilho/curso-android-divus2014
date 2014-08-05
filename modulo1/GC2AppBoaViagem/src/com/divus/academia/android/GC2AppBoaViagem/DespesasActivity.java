package com.divus.academia.android.GC2AppBoaViagem;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.divus.academia.android.GC2AppBoaViagem.adapter.DespesaAdapter;
import com.divus.academia.android.GC2AppBoaViagem.dao.DespesaDAO;
import com.divus.academia.android.GC2AppBoaViagem.model.Despesa;

public class DespesasActivity extends Activity implements OnItemClickListener {
	
	private ListView listViewDespesas;
	
	private List<Despesa> listaDespesas = new ArrayList<Despesa>();
	private DespesaDAO despesaDAO;
	private DespesaAdapter despesaAdapter;
	private Despesa despesaSelecionado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_despesas);
		/** Obt�m uma instancia do DAO de Despesa. */
		despesaDAO = DespesaDAO.getInstance(this);
		/** Obt�m o componente ListView da tela. */
		this.listViewDespesas = (ListView) findViewById(R.id.lstDespesas);
		
		// @ TODO Trocar por um m�todo.
		/** Lista no banco de dados todas as despesas. */
		this.listaDespesas = despesaDAO.listarTodos();
		/** Cria o Adapter com a lista de despesas. */
		this.despesaAdapter = new DespesaAdapter(this, listaDespesas);
		/** Seta no componente de listView o Adapter com a Lista de Despesas. */
		this.listViewDespesas.setAdapter(despesaAdapter);
		this.listViewDespesas.setOnItemClickListener(this);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {
			Despesa desp = (Despesa) data.getSerializableExtra("despesa");
			listaDespesas.add(desp);
		}
	}
	
	public void criarDespesaOnclick(View v) {
		Log.d("TelaDespesa", "m�todo criarDespesaOnclick");
		Toast.makeText(this, "m�todo criarDespesaOnclick", Toast.LENGTH_LONG).show();
		Intent intent = new Intent(this, CadastroDespesaActivity.class);
		startActivityForResult(intent, 0);
		
		// @ TODO Trocar por um m�todo
		/** Lista no banco de dados todas as despesas. */
		this.listaDespesas = despesaDAO.listarTodos();
		/** Cria o Adapter com a lista de despesas. */
		this.despesaAdapter = new DespesaAdapter(this, listaDespesas);
		/** Seta no componente de listView o Adapter com a Lista de Despesas. */
		this.listViewDespesas.setAdapter(despesaAdapter);
		this.listViewDespesas.setOnItemClickListener(this);
	}
	
	public void excluirDespesaOnclick(View v) {
		Log.d("TelaDespesa", "m�todo excluirDespesaOnclick");
		Toast.makeText(this, "m�todo excluirDespesaOnclick", Toast.LENGTH_LONG).show();
		if (despesaSelecionado != null) {
			despesaDAO.deletar(despesaSelecionado);
		} else {
			Toast.makeText(this, "Selecione um registro para poder excluir!", Toast.LENGTH_LONG).show();
		}
		
		// @ TODO Trocar por um m�todo
		/** Lista no banco de dados todas as despesas. */
		this.listaDespesas = despesaDAO.listarTodos();
		/** Cria o Adapter com a lista de despesas. */
		this.despesaAdapter = new DespesaAdapter(this, listaDespesas);
		/** Seta no componente de listView o Adapter com a Lista de Despesas. */
		this.listViewDespesas.setAdapter(despesaAdapter);
		this.listViewDespesas.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.despesas, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (despesaSelecionado != null) {
			despesaSelecionado.setSelecionado(false);
		}
		despesaSelecionado = listaDespesas.get(position);
		despesaSelecionado.setSelecionado(true);
		despesaAdapter.notifyDataSetChanged();
	}
}
