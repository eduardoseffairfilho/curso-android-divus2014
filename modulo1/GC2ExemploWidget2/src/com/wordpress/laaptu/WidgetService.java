package com.wordpress.laaptu;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class WidgetService extends RemoteViewsService {
	
	/**
	 * definição do adaptador do listview Aqui Adapter é ListProvider
	 */
	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent) {
//		int appWidgetId = intent.getIntExtra(
//				AppWidgetManager.EXTRA_APPWIDGET_ID,
//				AppWidgetManager.INVALID_APPWIDGET_ID);

		return (new ListProvider(this.getApplicationContext(), intent));
	}

}
