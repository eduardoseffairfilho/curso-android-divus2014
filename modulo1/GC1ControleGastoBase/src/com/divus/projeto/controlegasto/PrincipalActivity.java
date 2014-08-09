package com.divus.projeto.controlegasto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PrincipalActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_principal);
	}
	
	public void despesasOnclick(View view) {
		Intent intent = new Intent(this, DespesasActivity.class);
		startActivity(intent);
	}
	
}
