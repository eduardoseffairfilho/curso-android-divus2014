package com.divus.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class MensagensWidgetProvider extends AppWidgetProvider {
	
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

		// Inicializa Layout do Widget.
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
				R.layout.widget_layout);

		// Registrar evento do botão
		remoteViews.setOnClickPendingIntent(R.id.btnAtualizar, 
				buildButtonPendingIntent(context));

		// Atualizando componentes com valores iniciais.
		remoteViews.setTextViewText(R.id.titulo, getTitle());
		
		remoteViews.setTextViewText(R.id.descricao, getDesc());

		// Pedido para atualização do Widget.
		pushWidgetUpdate(context, remoteViews);
	}

	public static PendingIntent buildButtonPendingIntent(Context context) {
		// iniciar solicitação de atualização Widget.
		Intent intent = new Intent();
		intent.setAction(MensagensWidgetIntentReceiver.WIDGET_UPDATE_ACTION);
		return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
	}

	private static CharSequence getDesc() {
		return "Atualize para ver uma mensagem!";
	}

	private static CharSequence getTitle() {
		return "Mensagens Diárias";
	}

	public static void pushWidgetUpdate(Context context, RemoteViews remoteViews) {
		ComponentName myWidget = new ComponentName(context, MensagensWidgetProvider.class);
		AppWidgetManager manager = AppWidgetManager.getInstance(context);
		manager.updateAppWidget(myWidget, remoteViews);
	}
	
}
