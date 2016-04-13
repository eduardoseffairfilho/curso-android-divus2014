package com.divus.pushnotificacao;

import com.google.android.gcm.GCMRegistrar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class PrincipalActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		
		GCMRegistrar.checkDevice(this);
		GCMRegistrar.checkManifest(this);
	}

	public void ativarOnclick(View view) {
		Log.i("GCM", "REGISTRANDO DEVICE");
		GCMRegistrar.register(this, GCMIntentService.SEND_ID);
	}

}
