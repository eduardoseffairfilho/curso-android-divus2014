package com.divus.academia.android.GC2AppBoaViagem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class SegundaTelaActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_segunda_tela);
	}
	
	public void despesasOnclick(View v) {
		Log.d("SegundaTela", "método despesasOnclick");
		Intent intent = new Intent(this, DespesasActivity.class);
		startActivity(intent);
	}
	
	public void viagensOnclick(View v) {
		Log.d("SegundaTela", "método viagensOnclick");
		Toast.makeText(this, "método viagensOnclick", Toast.LENGTH_LONG).show();
	}
	
	public void minhasViagensOnclick(View v) {
		Log.d("SegundaTela", "método minhasViagensOnclick");
		Toast.makeText(this, "método minhasViagensOnclick", Toast.LENGTH_LONG).show();
	}
	
	public void configuracoesOnclick(View v) {
		Log.d("SegundaTela", "método configuracoesOnclick");
		Toast.makeText(this, "método configuracoesOnclick", Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.segunda_tela, menu);
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
