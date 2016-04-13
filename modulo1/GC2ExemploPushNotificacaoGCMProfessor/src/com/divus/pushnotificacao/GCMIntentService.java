package com.divus.pushnotificacao;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {

	public static final String MESSAGE = "message";
	
	public static final String SEND_ID = "699967688009";
	
	public GCMIntentService() {
		super(SEND_ID);
		Toast.makeText(this, "super", Toast.LENGTH_LONG).show();
	}
	
	@Override
	protected void onError(Context context, String errorId) {
		Toast.makeText(this, "onError", Toast.LENGTH_LONG).show();
		Log.i("GCM", "errorId= " + errorId);
	}

	@Override
	protected void onMessage(Context context, Intent data) {
		Toast.makeText(this, "onMessage", Toast.LENGTH_LONG).show();
		String mensagem = data.getStringExtra(MESSAGE);
		Log.i("GCM", "mesagemId= " + mensagem);
	}

	@Override
	protected void onRegistered(Context context, String registrationId) {
		Toast.makeText(this, "onRegistered", Toast.LENGTH_LONG).show();
		Log.i("GCM", "registrationId= " + registrationId);
	}

	@Override
	protected void onUnregistered(Context arg0, String arg1) {
	}

}
