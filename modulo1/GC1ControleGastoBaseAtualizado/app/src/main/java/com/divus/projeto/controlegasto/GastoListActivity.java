package com.divus.projeto.controlegasto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;

public class GastoListActivity extends ListActivity implements
		OnItemClickListener {

	private List<Map<String, Object>> gastos;
	private String dataAnterior = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Map<String, Object> map = gastos.get(position);
		String descricao = (String) map.get("descricao");
		Toast.makeText(this, "Gasto selecionada: " + descricao,
				Toast.LENGTH_SHORT).show();
	}

}
