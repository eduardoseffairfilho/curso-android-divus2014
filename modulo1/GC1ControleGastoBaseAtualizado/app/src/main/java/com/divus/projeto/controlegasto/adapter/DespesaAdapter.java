package com.divus.projeto.controlegasto.adapter;

import java.util.List;

import com.divus.projeto.controlegasto.R;
import com.divus.projeto.controlegasto.model.Despesa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class DespesaAdapter extends BaseAdapter {

	private Context context;
	
	private List<Despesa> despesas;
	
	public DespesaAdapter(Context context, List<Despesa> despesas) {
		this.context = context;
		this.despesas = despesas;
	} 
	
	@Override
	public int getCount() {
		return despesas.size();
	}

	@Override
	public Object getItem(int position) {
		return despesas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View view = inflater.inflate(R.layout.item_lista_despesa, parent, false);
		CheckBox ckSelecionado = (CheckBox) view.findViewById(R.id.ckSelecionado);
		TextView txDescricao = (TextView) view.findViewById(R.id.txDescricao);
		TextView txData = (TextView) view.findViewById(R.id.txData);
		
		Despesa despesa = despesas.get(position);
		
		if (despesa.isSelecionado()) {
			ckSelecionado.setChecked(true);
		} else {
			ckSelecionado.setChecked(false);
		}
		
		txDescricao.setText(despesa.getDescricao());
		txData.setText(despesa.getData());
		
		return view;
	}

}
