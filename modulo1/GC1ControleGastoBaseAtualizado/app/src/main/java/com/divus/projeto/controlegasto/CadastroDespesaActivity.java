package com.divus.projeto.controlegasto;

import java.util.Calendar;

import com.divus.projeto.controlegasto.dao.DespesaDAO;
import com.divus.projeto.controlegasto.model.Despesa;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CadastroDespesaActivity extends Activity {

	private Spinner spCategoria;
	
	private Button btnDataDespesa;
	
	private EditText edtDescricao;
	
	private EditText edtLocal;
	
	private EditText edtValor;
	
	private int ano, mes, dia;
	
	private DespesaDAO despesaDao;
	
	private Despesa despesa;
	
	private boolean isEdicao = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_despesa);
		
		despesaDao = DespesaDAO.getInstance(this);
		
		this.spCategoria = (Spinner) findViewById(R.id.categoria);
		this.btnDataDespesa = (Button) findViewById(R.id.data);
		this.btnDataDespesa.setText(inicializarData());
		
		this.edtLocal = (EditText) findViewById(R.id.local);
		this.edtValor = (EditText) findViewById(R.id.valor);
		this.edtDescricao = (EditText) findViewById(R.id.descricao);
		
		Bundle data = getIntent().getExtras();
		if(data != null 
				&& data.getSerializable(DespesasActivity.KEY_DESPESA) != null) {
			despesa = (Despesa) data.getSerializable(DespesasActivity.KEY_DESPESA);
			inicializarEdicao(despesa);
			isEdicao = true;
		}
	}
	
	public void inicializarEdicao(Despesa despesa) {
		edtDescricao.setText(despesa.getDescricao());
		edtLocal.setText(despesa.getLocal());
		edtValor.setText(despesa.getValor() + "");
		btnDataDespesa.setText(despesa.getData());
		
		String[] categorias = this.getResources()
				.getStringArray(R.array.categoria_despesas);
		
		for (int i = 0; i < categorias.length; i++) {
			if (categorias[i].equals(despesa.getCategoria())) {
				spCategoria.setSelection(i);
				break;
			}
		}
	}
	
	private String inicializarData() {
		Calendar calendar = Calendar.getInstance();

		ano = calendar.get(Calendar.YEAR);
		mes = calendar.get(Calendar.MONTH);
		dia = calendar.get(Calendar.DAY_OF_MONTH);
		return dia + "/" + (mes + 1) + "/" + ano;
	}

	private OnDateSetListener listener = new OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			ano = year;
			mes = monthOfYear;
			dia = dayOfMonth;
			btnDataDespesa.setText(dia + "/" + (mes + 1) + "/" + ano);
		}
	};
	
	@Override
	protected Dialog onCreateDialog(int id) {
		if (R.id.data == id) {
			return new DatePickerDialog(this, listener, ano, mes, dia);
		}
		return null;
	}
	
	public void selecionarDataOnclick(View view) {
		showDialog(view.getId());
	}
	
	public void salvarDespesaOnclick(View view) {
		if (!isEdicao) {
			despesa = new Despesa();
		}
		
		despesa.setDescricao(edtDescricao.getText().toString());
		despesa.setLocal(edtLocal.getText().toString());
		despesa.setValor(Double.valueOf(edtValor.getText().toString()));
		despesa.setData(btnDataDespesa.getText().toString());
		despesa.setCategoria(spCategoria.getSelectedItem().toString());
		
		try {
			if(isEdicao){
				despesaDao.update(despesa);
			} else {
				despesaDao.salvar(despesa);
			}
			Intent data = new Intent();
			data.putExtra("despesa", despesa);
			setResult(RESULT_OK, data);
			finish();			
		} catch (Exception e) {
			Log.e("ERRO", "Falha ao Salvar!");
			Toast.makeText(this, "Falha ao Salvar!", Toast.LENGTH_LONG).show();
		}
		
	}
	
}
