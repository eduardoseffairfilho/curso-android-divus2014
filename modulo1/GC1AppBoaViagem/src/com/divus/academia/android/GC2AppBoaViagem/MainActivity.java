package com.divus.academia.android.GC2AppBoaViagem;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends Activity {

	private EditText edtUsuario;
	private EditText edtSenha;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		edtUsuario = (EditText) findViewById(R.id.edtUsuario);
		edtUsuario.setText("admin"); // @ TODO Remover depois;
		edtSenha = (EditText) findViewById(R.id.edtSenha);
		edtSenha.setText("1234"); // @ TODO Remover depois;
	}
	
	public void loginOnclick(View v) {
		String strUsuario = null;
		if (edtUsuario != null && edtUsuario.getText() != null) {
			strUsuario = edtUsuario.getText().toString();
		}
		
		String strSenha = null;
		if (edtSenha != null && edtSenha.getText() != null) {
			strSenha = edtSenha.getText().toString();
		}

		/** Verifica o usuário*/
		if (strUsuario != null && strUsuario.compareTo("admin") == 0) {
			/** Verifica a senha do usuário. */
			if (strSenha != null && strSenha.compareTo("1234") == 0) {
				Intent intent = new Intent(this, SegundaTelaActivity.class);
				startActivity(intent);
			} else {
				emitirAlerta("Login", "Senha inválida!");
			}
		} else {
			emitirAlerta("Login", "Usuário inválido!");
		}
	}
/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
	}*/

	protected void emitirAlerta(String title, String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setNeutralButton("OK", null);
		
		AlertDialog alerta = builder.create();
		alerta.show();
	}
}
