package com.divus.academia.android.GC2ExemploAula2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.divus.academia.android.GC2ExemploAula2.R;

public class TelaSegundaActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_tela_segunda);

		String nome = null;
		Bundle data = getIntent().getExtras();
		if (data != null) {
			nome = data.getString("nome");
		}
		
		TextView txNome = (TextView) findViewById(R.id.txResultadoNome);
		if (nome != null && nome.length() > 0) {
			txNome.setText("O nome é: " + nome);
		} else {
			txNome.setText("O nome não foi informado!");
		}
	}
}
