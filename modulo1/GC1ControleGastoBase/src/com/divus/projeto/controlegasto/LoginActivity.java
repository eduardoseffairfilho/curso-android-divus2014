package com.divus.projeto.controlegasto;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends Activity {

	private static final String MANTER_CONECTADO = "manter_conectado";
	
	private EditText edtUsuario;
	
	private EditText edtSenha;
	
	private CheckBox ckManterConectado;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		edtUsuario = (EditText) findViewById(R.id.edtUsuario);
		edtSenha = (EditText) findViewById(R.id.edtSenha);
		
		ckManterConectado = (CheckBox) findViewById(R.id.ckManterConectado);
		
		SharedPreferences shared = getPreferences(MODE_PRIVATE);
		
		boolean conectado = shared.getBoolean(MANTER_CONECTADO, false);
		if (conectado) {
			direcionarTelaPrincipal();
		}
	}

	public void direcionarTelaPrincipal() {
		Intent intent = new Intent(this, PrincipalActivity.class);
		startActivity(intent);
		finish();
	}
	
	public void loginOnClick(View v) {
		if (edtUsuario.getText().toString().equals("admin")
				&& edtSenha.getText().toString().equals("123")) {
			
			SharedPreferences shared = getPreferences(MODE_PRIVATE);
			
			Editor edit = shared.edit(); 
			edit.putBoolean(MANTER_CONECTADO, ckManterConectado.isChecked());
			edit.commit();

			direcionarTelaPrincipal();
		} else {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Acesso Incorreto!");
			builder.setNeutralButton("Ok", null);
			
			AlertDialog alerta = builder.create();
			alerta.setTitle("Login");
			alerta.show();
		}
	}
	
}
