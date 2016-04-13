package com.divus.academia.android.GC2AppBoaViagem;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.divus.academia.android.GC2AppBoaViagem.dao.DespesaDAO;
import com.divus.academia.android.GC2AppBoaViagem.model.Despesa;

public class CadastroDespesaActivity extends Activity {
	
	private Spinner spnCategoria;
	private Button btnDataDespesa;
	private EditText edtDescricao;
	private EditText edtValorDespesa;
	private EditText edtLocalDespesa;
	
	private int ano, mes, dia;
	
	private DespesaDAO despesaDAO;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_despesa);
		
		despesaDAO = DespesaDAO.getInstance(this);
		
		this.spnCategoria = (Spinner) findViewById(R.id.spnCategoria);
		this.edtDescricao = (EditText) findViewById(R.id.edtDescricao);
		this.edtValorDespesa = (EditText) findViewById(R.id.edtValorDespesa);
		this.edtLocalDespesa = (EditText) findViewById(R.id.edtLocalDespesa);
		this.btnDataDespesa = (Button) findViewById(R.id.btnDataDespesa);
		this.btnDataDespesa.setText(inicializarData());
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		if (R.id.btnDataDespesa == id) {
			return new DatePickerDialog(this, listener, ano, mes, dia);
		}
		return super.onCreateDialog(id);
	}
	
	public void selecionarDataOnclick(View v) { 
		showDialog(v.getId());
	}
	
	public void salvarDespesaOnclick(View v) {
		Despesa despesa = new Despesa();
		despesa.setDescricao(edtDescricao.getText().toString());
		despesa.setLocal(edtLocalDespesa.getText().toString());
		despesa.setData(btnDataDespesa.getText().toString());
		despesa.setCategoria(spnCategoria.getSelectedItem().toString());
		despesa.setValor(Double.valueOf(edtValorDespesa.getText().toString()));
		
		try {
			despesaDAO.salvar(despesa);
			
			Intent intent = new Intent();
			intent.putExtra("despesa", despesa);
			
			setResult(Activity.RESULT_OK, intent);
			finish();
		} catch (Exception e) {
			Log.e("ERRO", "Falha ao salvar Despesa!");
			Toast.makeText(this, "Falha na operação, não foi possível salvar a despesa. Tente novamente!", Toast.LENGTH_LONG).show();
		}
	}
	
	private String inicializarData() {
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
