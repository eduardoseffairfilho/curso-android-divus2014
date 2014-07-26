package com.divus.academia.android.GC2AppBoaViagem;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

public class CadastroDespesaActivity extends Activity {
	
	private Spinner spnCategoria;
	private Button btnDataDespesa;
	private EditText edtDescricao;
	private int ano, mes, dia;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_despesa);
		
		this.spnCategoria = (Spinner) findViewById(R.id.spnCategoria);
		this.btnDataDespesa = (Button) findViewById(R.id.btnData);
		this.edtDescricao = (EditText) findViewById(R.id.edtDescricao);
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		if (R.id.btnData == id) {
			return new DatePickerDialog(this, listener, ano, mes, dia);
		}
		return super.onCreateDialog(id);
	}
	
	public void selecionarDataOnclick(View v) { 
		showDialog(v.getId());
	}
	
	public String inicializarData() {
		Calendar calendar = Calendar.getInstance();
		
		ano = calendar.get(Calendar.YEAR);
		mes = calendar.get(Calendar.MONTH);
		dia = calendar.get(Calendar.DAY_OF_MONTH);
		
		return dia + "/" + (mes+1) + "/" + ano;
	}
	
	private OnDateSetListener listener = new OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			ano = year;
			mes = monthOfYear;
			dia = dayOfMonth;
			btnDataDespesa.setText(dia + "/" + (mes+1) + "/" + ano);
		}
	};
}
