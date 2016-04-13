package com.example.gc1exemplobroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AcaoDepoisBootBroadcast extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "Sistema Funcionando...", Toast.LENGTH_LONG).show();
	}

}
