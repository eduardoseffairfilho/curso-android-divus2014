package com.example.widgetmensagem;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class MensagemWidgetProvider extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		
		RemoteViews viewRemota = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
		
		viewRemota.setTextViewText(R.id.txtTitulo, "Mensagem do Dia");
		viewRemota.setTextViewText(R.id.txtMensagem, "Atualize para ver uma mensagem!");
		
		viewRemota.setOnClickPendingIntent(R.id.btnRefresh, criaAcao(context));
		
		viewRemota.setOnClickPendingIntent(R.id.btnOpen, chamarActivity(context));
		
		atualizarWidget(context, viewRemota);
	}

	public static PendingIntent chamarActivity(Context context) {
		Intent intent = new Intent(context, MainActivity.class);
		
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 
				PendingIntent.FLAG_UPDATE_CURRENT);
		
		return pendingIntent; 
	}

	public static PendingIntent criaAcao(Context context) {
		Intent intent = new Intent();
		intent.setAction(AtualizaMensagemWidgetReceiver.UPDATE_ACTION);
		
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 
				PendingIntent.FLAG_UPDATE_CURRENT);
		
		return pendingIntent; 
	}
	
	public static void atualizarWidget(Context context, RemoteViews remoteView)	{
		ComponentName  componente = new ComponentName(context, MensagemWidgetProvider.class);
		AppWidgetManager manager = AppWidgetManager.getInstance(context);
		manager.updateAppWidget(componente, remoteView);
	}

}
