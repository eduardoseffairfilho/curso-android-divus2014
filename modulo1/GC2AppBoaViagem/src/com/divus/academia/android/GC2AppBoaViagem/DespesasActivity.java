package com.divus.academia.android.GC2AppBoaViagem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class DespesasActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_despesas);
	}
	
	public void criarDespesaOnclick(View v) {
		Log.d("TelaDespesa", "método criarDespesaOnclick");
		Toast.makeText(this, "método criarDespesaOnclick", Toast.LENGTH_LONG).show();
		Intent intent = new Intent(this, CadastroDespesaActivity.class);
		startActivity(intent);
	}
	
	public void excluirDespesaOnclick(View v) {
		Log.d("TelaDespesa", "método excluirDespesaOnclick");
		Toast.makeText(this, "método excluirDespesaOnclick", Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.despesas, menu);
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
}
