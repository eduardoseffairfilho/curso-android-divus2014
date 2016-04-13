package com.wordpress.laaptu;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

public class WidgetProvider extends AppWidgetProvider {

	/* 
	 * Este m�todo � chamado a cada 30 minutos, conforme especificado no widgetinfo.xml
	 * Este m�todo tamb�m � chamado em cada reinicializa��o do telefone.
	 */
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		final int N = appWidgetIds.length;
		/* Int [] appWidgetIds det�m ids de v�rias inst�ncias do seu widget
		* Ou seja, voc� est� colocando mais de um widgets em sua tela inicial */
		for (int i = 0; i < N; ++i) {
			RemoteViews remoteViews = updateWidgetListView(context,
					appWidgetIds[i]);
			appWidgetManager.updateAppWidget(appWidgetIds[i], remoteViews);
		}
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}

	private RemoteViews updateWidgetListView(Context context, int appWidgetId) {

		//Configura o layout a ser mostrado
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
				R.layout.widget_layout);
		
		/// Servi�o RemoteViews necess�rio para fornecer adaptador para ListView
		Intent svcIntent = new Intent(context, WidgetService.class);
		//Passar id aplicativo widget para que o Servi�o RemoteViews
		svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);

		svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
		
		//Configurando adapter do listview para o widget
		remoteViews.setRemoteAdapter(appWidgetId, R.id.listViewWidget, svcIntent);
		
		//Definindo uma vis�o vazia em caso de n�o dados
		remoteViews.setEmptyView(R.id.listViewWidget, R.id.empty_view);
		
		return remoteViews;
	}

}
