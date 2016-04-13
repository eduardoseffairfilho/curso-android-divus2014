package com.example.gc1exemploservico.servico;

import android.app.IntentService;
import android.content.Intent;

public class ContadorServico extends IntentService {	
	
	public static final String VALOR = "valor";
	public static final String NOTIFICAR = "br.divus.service";
	
	private Integer contador = 0;
	private Integer time = 1;

	public ContadorServico() {
		super("ContadorServico");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		while(true) {
			if (time % 1000000 == 0) {
				publicarContagem();
				contador++;
			}
			time++;
		}
	}
	
	public void publicarContagem() {
		Intent it = new Intent(NOTIFICAR);
		it.putExtra(VALOR, contador);
		sendBroadcast(it);
	}
}
