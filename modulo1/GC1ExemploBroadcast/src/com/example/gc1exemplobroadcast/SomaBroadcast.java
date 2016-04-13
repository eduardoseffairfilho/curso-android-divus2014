package com.example.gc1exemplobroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * O BROADCAST DEVE SER REGISTRADO OU VIA ACTIVITY OU VIA ANDROID-MANIFESTO.
 * CASO CONTR�RIO ELE PODER� N�O FUNCIONAR ADEQUADAMENTE.
 * 
 * @author eduardo
 *
 */
public class SomaBroadcast extends BroadcastReceiver {
	
	public static String VALOR_UM = "valor1";
	public static String VALOR_DOIS = "valor2";

	@Override
	public void onReceive(Context context, Intent intent) {
		Integer vlr1 = intent.getExtras().getInt(VALOR_UM);
		Integer vlr2 = intent.getExtras().getInt(VALOR_DOIS);
		
		Integer soma = vlr1 + vlr2;
		Toast.makeText(context, "O resultado da opera��o �: " + soma, Toast.LENGTH_LONG).show();
	}
}
