package com.example.gc1exemploservico;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.gc1exemploservico.servico.ContadorServico;

public class PrincipalActivity extends Activity {
	
	private TextView txtContador;
	private Intent it;
	
	private BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				Integer cont = bundle.getInt(ContadorServico.VALOR);
				txtContador.setText(cont.toString());
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_principal);
		
		txtContador = (TextView) findViewById(R.id.txtContador);
		
		it = new Intent(this, ContadorServico.class);
		startService(it);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		registerReceiver(receiver, new IntentFilter(ContadorServico.NOTIFICAR));
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		unregisterReceiver(receiver);
	}
	
	public void proximoOnclick(View v) {
		Intent itSegundaTela = new Intent(this, SegundaActivity.class);
		startActivity(itSegundaTela);
	}
}
