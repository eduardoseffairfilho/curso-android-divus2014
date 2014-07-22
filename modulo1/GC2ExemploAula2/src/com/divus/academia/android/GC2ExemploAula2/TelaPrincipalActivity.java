package com.divus.academia.android.GC2ExemploAula2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.divus.academia.android.GC2ExemploAula2.R;

public class TelaPrincipalActivity extends Activity {
	
	private Button btnNavegar;
	private EditText edtNome;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_principal);
		
		/** Obt�m a instancia do elemento do EditText. */
		edtNome = (EditText) findViewById(R.id.edtNome);
		
		/** Obt�m a instancia do elemento do Bot�o. */
		btnNavegar = (Button) findViewById(R.id.btnNavegar);
		
		/** Cria o evento no Clique do Bot�o. */
		btnNavegar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//Toast.makeText(TelaPrincipalActivity.this, "Mew Nome: " + edtNome.getText().toString(), Toast.LENGTH_LONG).show();
				
				Intent it = new Intent(TelaPrincipalActivity.this, TelaSegundaActivity.class);
				it.putExtra("nome", edtNome.getText().toString());
				startActivity(it);
			}
		});
	}
}
