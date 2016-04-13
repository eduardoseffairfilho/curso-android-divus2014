package com.example.widgetmensagem;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class AtualizaMensagemWidgetReceiver extends BroadcastReceiver {
	
	public static final String UPDATE_ACTION = "br.com.divus.mensagem";

	private static int contadorClick = 0;
	private String[] mensagens = null;
	private Context context;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		this.context = context;
		if (intent.getAction().equals(UPDATE_ACTION)) {
			atualizarMensagem(context);
		}
	}

	private void atualizarMensagem(Context context) {
		RemoteViews viewRemota = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
		viewRemota.setTextViewText(R.id.txtMensagem, getMensagem());
		viewRemota.setTextViewText(R.id.txtTitulo, "Mensagem do dia");
		viewRemota.setOnClickPendingIntent(R.id.btnRefresh, MensagemWidgetProvider.criaAcao(context));
		viewRemota.setOnClickPendingIntent(R.id.btnRefresh, MensagemWidgetProvider.criaAcao(context));
		
		MensagemWidgetProvider.atualizarWidget(context, viewRemota);
		
		contadorClick++;
	}

	private String getMensagem() {
		mensagens = context.getResources().getStringArray(R.array.news_headlines);
		if (contadorClick >= mensagens.length) {
			contadorClick = 0;
		}
		return mensagens[contadorClick];
	}
}
