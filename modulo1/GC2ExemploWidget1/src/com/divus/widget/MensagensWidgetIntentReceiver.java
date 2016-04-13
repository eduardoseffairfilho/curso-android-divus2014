package com.divus.widget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class MensagensWidgetIntentReceiver extends BroadcastReceiver {
	
	public final static String WIDGET_UPDATE_ACTION ="com.divus.widget.intent.action.UPDATE_WIDGET";
	
	public static int clickContador = 0;
	
	private String msg[] = null;

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(WIDGET_UPDATE_ACTION)) {
			updateWidgetPictureAndButtonListener(context);
		}
	}

	private void updateWidgetPictureAndButtonListener(Context context) {
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
				R.layout.widget_layout);

		// updating view
		remoteViews.setTextViewText(R.id.titulo, getTitle());
		remoteViews.setTextViewText(R.id.descricao, getDesc(context));

		// re-registering for click listener
		remoteViews.setOnClickPendingIntent(R.id.btnAtualizar,
				MensagensWidgetProvider.buildButtonPendingIntent(context));

		MensagensWidgetProvider.pushWidgetUpdate(context.getApplicationContext(), remoteViews);
		clickContador++;
	}

	private String getDesc(Context context) {
		msg = context.getResources().getStringArray(R.array.news_headlines);
		if (clickContador >= msg.length) {
			clickContador = 0;
		}
		return msg[clickContador];
	}

	private String getTitle() {
		return "Mensagens Diárias";
	}
}
