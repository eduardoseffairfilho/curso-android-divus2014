package com.example.gc1exemplobroadcast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

/**
 * O BROADCAST DEVE SER REGISTRADO OU VIA ACTIVITY OU VIA ANDROID-MANIFESTO.
 * CASO CONTRÁRIO ELE PODERÁ NÃO FUNCIONAR ADEQUADAMENTE.
 * 
 * @author eduardo
 *
 */
public class MainActivity extends Activity {
	
	private EditText valorUm;
	private EditText valorDois;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		valorUm = (EditText) findViewById(R.id.edtValorUm);
		valorDois = (EditText) findViewById(R.id.edtValorDois);
	}

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
	}
	
	public void calcularOnclick(View v) {
		Intent intent = new Intent(this, SomaBroadcast.class);
		Integer vlr1 = Integer.valueOf(valorUm.getText().toString());
		Integer vlr2 = Integer.valueOf(valorDois.getText().toString());
		intent.putExtra(SomaBroadcast.VALOR_UM, vlr1);
		intent.putExtra(SomaBroadcast.VALOR_DOIS, vlr2);
		sendBroadcast(intent);
	}
}
