package com.divus.academia.android.GC2AppBoaViagem.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.divus.academia.android.GC2AppBoaViagem.R;
import com.divus.academia.android.GC2AppBoaViagem.model.Despesa;

public class DespesaAdapter extends BaseAdapter {
	private Context context;
	private List<Despesa> listaDespesa;
	
	public DespesaAdapter(Context context, List<Despesa> listaDespesas) {
		this.context = context;
		this.listaDespesa = listaDespesas;
	}

	@Override
	public int getCount() {
		int size = 0;
		if (listaDespesa != null) {
			size = listaDespesa.size();
		}
		return size;
	}

	@Override
	public Object getItem(int position) {
		return listaDespesa.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.item_list_despesa, parent, false);
		CheckBox chkSelecionado = (CheckBox) view.findViewById(R.id.chkSelecionado);
		TextView txtDescricao = (TextView) view.findViewById(R.id.txtDescricao);
		TextView txtData = (TextView) view.findViewById(R.id.txtData);
		
		Despesa despesa = listaDespesa.get(position);
		chkSelecionado.setChecked(despesa.isSelecionado());
		txtDescricao.setText(despesa.getDescricao());
		txtData.setText(despesa.getData());
		
		return view;
	}

}
